package com.erp.web;

import com.erp.dao.FactureDAO;
import com.erp.model.*;
import com.erp.enums.RoleUtilisateur;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet("/factures")
public class FactureServlet extends HttpServlet {
    private FactureDAO factureDAO = new FactureDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        String action = req.getParameter("action");

        // Filtrage de sécurité : Le client ne voit que ses propres factures
        List<Facture> toutes = factureDAO.findAll();
        List<Facture> affichables = (user.getRole() == RoleUtilisateur.CLIENT)
                ? toutes.stream().filter(f -> f.getCommande().getClient().getId().equals(user.getClient().getId())).collect(Collectors.toList())
                : toutes;

        if ("view".equals(action) || "download".equals(action)) {
            Long id = Long.parseLong(req.getParameter("id"));
            Facture f = factureDAO.find(id);

            if (user.getRole() == RoleUtilisateur.CLIENT && !f.getCommande().getClient().getId().equals(user.getClient().getId())) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            if ("view".equals(action)) {
                req.setAttribute("facture", f);
                req.getRequestDispatcher("/views/facture_detail.jsp").forward(req, resp);
            } else {
                genererPdf(f, resp);
            }
        } else {
            req.setAttribute("factures", affichables);
            req.getRequestDispatcher("/views/factures.jsp").forward(req, resp);
        }
    }

    private void genererPdf(Facture f, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=Facture_" + f.getId() + ".pdf");

        // Formatage avec séparateur de milliers (espace)
        DecimalFormatSymbols s = new DecimalFormatSymbols(Locale.FRENCH);
        s.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,###", s);

        try (Document doc = new Document()) {
            PdfWriter.getInstance(doc, resp.getOutputStream());
            doc.open();
            Font bold = new Font(Font.HELVETICA, 12, Font.BOLD);

            doc.add(new Paragraph("FACTURE N° FAC-" + f.getId(), new Font(Font.HELVETICA, 18, Font.BOLD)));
            doc.add(new Paragraph("Client : " + f.getCommande().getClient().getNom() + " " + f.getCommande().getClient().getPrenom()));
            doc.add(new Paragraph("Date : " + f.getDateFacturation()));
            doc.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell(new Phrase("Produit", bold)); table.addCell(new Phrase("P.U.", bold));
            table.addCell(new Phrase("Qté", bold)); table.addCell(new Phrase("Total", bold));

            for (LigneCommande l : f.getCommande().getLignes()) {
                table.addCell(l.getProduit().getLibelle());
                table.addCell(df.format(l.getProduit().getPrixUnitaire()) + " Ar");
                table.addCell(String.valueOf(l.getQuantite()));
                table.addCell(df.format(l.getPrixVenteTotal()) + " Ar");
            }
            doc.add(table);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("TOTAL TTC : " + df.format(f.getMontantTotalTTC()) + " Ar", new Font(Font.HELVETICA, 14, Font.BOLD)));
        } catch (Exception e) { throw new IOException(e); }
    }
}