package com.company.fileService;

import com.company.domian.Resolution;
import com.company.domian.Static;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class createFileResolution {

    //windows
    private String uploadPathResolution = "C:\\DocumentLift\\config\\resolve\\";
    private String uploadPathCaption = "C:\\DocumentLift\\config\\caption\\";
    private String uploadFileSystem = "C:\\DocumentLift\\system\\";
    private String fontPath = "C:\\Windows\\Fonts\\";

    //linux
//    private String uploadPathResolution = "/home/dikaya/DocumentLift/config/resolve/";
//    private String uploadPathCaption = "/home/dikaya/DocumentLift/config/caption/";
//    private String uploadFileSystem = "/home/dikaya/DocumentLift/system/";
//    private String fontPath = "/home/dikaya/DocumentLift/font/";

//    @Value("${upload.path.caption}")
//    private String uploadPathCaption;
//    @Value("${upload.path.resolve}")
//    private String uploadPathResolution;


    static int FONT_SIZE_SMALL = 14;
    static int FONT_SIZE_POST = 17;



    public String formRes(Resolution resolution, Iterable<Static> statics, Boolean caption) throws IOException, DocumentException {
//windows
        File uploadDir = new File(this.uploadPathResolution);
// linux
//        File uploadDir = new File(this.uploadPathResolution + "/" + dataFile.geteTodayYer());

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        BaseColor color_1 = new BaseColor(51, 51, 153);

        Document document = new Document(PageSize.A6);
        BaseFont bfB = BaseFont.createFont(fontPath + "timesbd.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font fontBig = new Font(bfB, FONT_SIZE_POST);
        fontBig.setColor(color_1);

        BaseFont bfA = BaseFont.createFont(fontPath + "timesbi.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font fontAgrees = new Font(bfB, FONT_SIZE_POST);
        fontAgrees.setColor(color_1);
        fontAgrees.setStyle(Font.ITALIC);


        BaseFont bfS = BaseFont.createFont(fontPath + "Arial.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font fontSmall = new Font(bfS, FONT_SIZE_SMALL, Font.BOLD);
        fontSmall.setColor(color_1);

        BaseFont bfD = BaseFont.createFont(fontPath + "timesi.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font fontDoer = new Font(bfD, FONT_SIZE_SMALL);

        BaseFont bfI = BaseFont.createFont(fontPath + "timesbd.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font fontInit = new Font(bfI, FONT_SIZE_SMALL);

        String uuidFile = UUID.randomUUID().toString();
        PdfWriter.getInstance(document,
//windows
                new FileOutputStream(this.uploadPathResolution + "\\" + uuidFile + ".pdf"));
//linux
//                new FileOutputStream(this.uploadPathResolution + "/" + dataFile.geteTodayYer() + "/" + uuidFile + ".pdf"));
        document.open();


        //post
        if (resolution.getVisa() != null) {
            if (resolution.getVisa().getPosition() != null) {
                Paragraph amount = new Paragraph();
                amount.setFont(fontBig);
                amount.setAlignment(Element.ALIGN_CENTER);
                amount.add(new Chunk(resolution.getVisa().getPosition()));
                document.add(amount);
            }
        }
        Paragraph sud1 = new Paragraph();
        sud1.setFont(fontBig);
        sud1.setAlignment(Element.ALIGN_CENTER);
        sud1.add(new Chunk("Сьомого апеляційного"));
        document.add(sud1);

        Paragraph sud2 = new Paragraph();
        sud2.setFont(fontBig);
        sud2.setAlignment(Element.ALIGN_CENTER);
        sud2.setSpacingAfter(8);
        sud2.add(new Chunk("адміністративного суду"));
        document.add(sud2);


        // Blue line

        Image stamp2 = Image.getInstance(uploadFileSystem + "line.png");
        stamp2.scaleAbsolute(250,5);
        stamp2.setAlignment(Element.ALIGN_CENTER);
        document.add(stamp2);

        //performers
        statics.forEach(stat-> {
            Paragraph perf = new Paragraph();
            perf.setFont(fontInit);
            perf.setAlignment(Element.ALIGN_CENTER);
            perf.setSpacingAfter(4);
            perf.add(new Chunk(stat.getInitials()));
            try {
                document.add(perf);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            if (stat.getDoer() != null) {
                Paragraph com = new Paragraph();
                com.setFont(fontDoer);
                com.setAlignment(Element.ALIGN_CENTER);
                com.setSpacingAfter(9);
                com.add(new Chunk(stat.getDoer()));
                try {
                    document.add(com);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });

        //caption
        if (caption){
            Image cap = Image.getInstance(uploadPathCaption + resolution.getAgrees().getInformation().getPerson().getCaption());
            cap.scaleAbsolute(100,100);
            cap.setAlignment(Element.ALIGN_CENTER);
            cap.setSpacingAfter(10);
            document.add(cap);
        }else {
            Paragraph cap = new Paragraph();
            cap.setSpacingAfter(100);
            document.add(cap);

        }


        //agress
        Paragraph agees = new Paragraph();
        agees.setFont(fontAgrees);
        agees.setAlignment(Element.ALIGN_RIGHT);
        if (resolution.getVisa() != null) {
            agees.add(new Chunk(resolution.getVisa().getAgrees()));
        }else {
            agees.add(new Chunk(resolution.getAgrees().getInformation().getPerson().getInitials()));
        }
        document.add(agees);

        //agress
        Paragraph date = new Paragraph();
        date.setFont(fontAgrees);
        date.setAlignment(Element.ALIGN_RIGHT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        date.add(new Chunk(resolution.getDate().format(formatter)));
        document.add(date);


        document.close();

        return uuidFile + ".pdf";
    }
}
