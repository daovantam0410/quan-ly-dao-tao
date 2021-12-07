package com.daovantam.quanlydaotao.utils;

import com.daovantam.quanlydaotao.entity.Student;
import com.daovantam.quanlydaotao.model.request.student.StudentRequest;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PDFGenerator {

    public static ByteArrayInputStream exportToPDF(List<StudentResponse> students){
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("List Student", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable = new PdfPTable(10);

            Stream.of("ID", "MSV", "Họ và tên", "Email", "SĐT", "Địa chỉ", "Năm sinh", "Thời gian đào tạo", "Khoa", "Lớp")
            .forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                pdfPTable.addCell(header);
            });

            for (StudentResponse student:students) {
                PdfPCell idCell = new PdfPCell(new Phrase(student.getId()));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(idCell);

                PdfPCell mSVCell = new PdfPCell(new Phrase(student.getCode()));
                mSVCell.setPaddingLeft(4);
                mSVCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mSVCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(mSVCell);

                PdfPCell fullNameCell = new PdfPCell(new Phrase(student.getFullName()));
                fullNameCell.setPaddingLeft(4);
                fullNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                fullNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(fullNameCell);

                PdfPCell emailCell = new PdfPCell(new Phrase(student.getEmail()));
                emailCell.setPaddingLeft(4);
                emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(emailCell);

                PdfPCell phoneCell = new PdfPCell(new Phrase(student.getPhone()));
                phoneCell.setPaddingLeft(4);
                phoneCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                phoneCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(phoneCell);

                PdfPCell addressCell = new PdfPCell(new Phrase(student.getAddress()));
                addressCell.setPaddingLeft(4);
                addressCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                addressCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(addressCell);

                PdfPCell dateCell = new PdfPCell(new Phrase(student.getDateOfBirth().toString()));
                dateCell.setPaddingLeft(4);
                dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(dateCell);

                PdfPCell timeCell = new PdfPCell(new Phrase(student.getTime()));
                timeCell.setPaddingLeft(4);
                timeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                timeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(timeCell);

                PdfPCell branchCell = new PdfPCell(new Phrase(student.getBranchName()));
                branchCell.setPaddingLeft(4);
                branchCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                branchCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(branchCell);

                PdfPCell roomCell = new PdfPCell(new Phrase(student.getRoomName()));
                roomCell.setPaddingLeft(4);
                roomCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                roomCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(roomCell);
            }

            document.add(pdfPTable);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
