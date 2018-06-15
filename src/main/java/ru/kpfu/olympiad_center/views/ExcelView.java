package ru.kpfu.olympiad_center.views;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import ru.kpfu.olympiad_center.models.Olympiad;
import ru.kpfu.olympiad_center.models.OlympiadParticipation;
import ru.kpfu.olympiad_center.models.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {
    public static final String DATE_PATTERN = "dd.MM.yyyy";
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=" + "report" + new Date().getTime() + ".xls");

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);

        int rowNumber = 0;
        int recordNumber = 1;
        int studentsCount;
        Row row;
        Cell userFioCell;
        Cell userRoleCell;
        Sheet reportSheet = workbook.createSheet("report");

        CellStyle centeredStyle = createCenteredStyle(workbook);
        CellStyle leftCenteredStyle = createLeftAndCenteredStyle(workbook);
        CellStyle boldGrayCenteredStyle = createBoldAndGrayAndCenteredStyle(workbook);

        rowNumber = createHeader(reportSheet, rowNumber, boldGrayCenteredStyle);

        List<Olympiad> olympiads = (List<Olympiad>) model.get("olympiads");
        if (!olympiads.isEmpty()) {
            for (Olympiad olympiad : olympiads) {
                row = reportSheet.createRow(rowNumber);

                List<OlympiadParticipation> olympiadParticipationList = olympiad.getOlympiadParticipation();
                studentsCount = olympiadParticipationList.size();
                markMergedRegions(reportSheet, rowNumber, studentsCount);
                recordNumber = setValuesIntoMergedCells(olympiad, row, recordNumber, centeredStyle, dateFormat);

                for (OlympiadParticipation olympiadParticipation : olympiadParticipationList) {
                    Student student = olympiadParticipation.getStudent();

                    userFioCell = row.createCell(2);
                    userRoleCell = row.createCell(3);

                    userFioCell.setCellStyle(leftCenteredStyle);
                    userRoleCell.setCellStyle(leftCenteredStyle);

                    userFioCell.setCellValue(
                            String.valueOf(student.getLastName() + " " + student.getFirstName() + " " + student.getMiddleName()));
                    userRoleCell.setCellValue(student.getRole().rusName());
                    reportSheet.getRow(rowNumber++);
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            reportSheet.autoSizeColumn(i);
        }

    }

    private void markMergedRegions(Sheet reportSheet, int rowNumber, int studentsCount) {
        if (rowNumber < studentsCount && studentsCount > 1) {
            reportSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber + studentsCount - 1, 0, 0));
            reportSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber + studentsCount - 1, 1, 1));
            reportSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber + studentsCount - 1, 4, 4));
            reportSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber + studentsCount - 1, 5, 5));
            reportSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber + studentsCount - 1, 6, 6));
        }
    }

    private int setValuesIntoMergedCells(Olympiad olympiad, Row row, int recordNumber, CellStyle cellStyle, DateTimeFormatter dateFormat) {
        Cell cell;

        String awardType = olympiad
                .getOlympiadParticipation()
                .get(0)
                .getAwardType()
                .rusName();

        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(recordNumber++);

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(olympiad.getName());

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(awardType);

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(olympiad.getLevel().rusName());


        String formatBeginDate = olympiad.getBeginDate().format(dateFormat);
        String formatEndDate = olympiad.getEndDate().format(dateFormat);
        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(String.valueOf(formatBeginDate + " - " + formatEndDate));

        return recordNumber;
    }

    private int createHeader(Sheet reportSheet, int rowNumber, CellStyle cellStyle) {
        Row row = reportSheet.createRow(rowNumber++);
        Cell cell;

        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("№");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Наименование награды");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Список награжденных (Ф.И.О.)");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Категория участника");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Вид награды");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Уровень награды");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Дата награждения (дд.мм.гггг)");

        row = reportSheet.createRow(rowNumber++);

        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(1);

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(2);

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(3);

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(4);

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(5);

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(6);

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(7);

        return rowNumber;
    }

    private CellStyle createCenteredStyle(Workbook workbook) {
        CellStyle centeredStyle = workbook.createCellStyle();
        centeredStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        centeredStyle.setAlignment(HorizontalAlignment.CENTER);
        centeredStyle.setWrapText(true);

        return centeredStyle;
    }

    private CellStyle createLeftAndCenteredStyle(Workbook workbook) {
        CellStyle leftAndCenteredStyle = workbook.createCellStyle();
        leftAndCenteredStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        leftAndCenteredStyle.setAlignment(HorizontalAlignment.LEFT);

        return leftAndCenteredStyle;
    }

    private CellStyle createBoldAndGrayAndCenteredStyle(Workbook workbook) {
        CellStyle boldAndGrayAndCenteredStyle = workbook.createCellStyle();

        boldAndGrayAndCenteredStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        boldAndGrayAndCenteredStyle.setAlignment(HorizontalAlignment.CENTER);
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldAndGrayAndCenteredStyle.setFont(boldFont);
        boldAndGrayAndCenteredStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        boldAndGrayAndCenteredStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return boldAndGrayAndCenteredStyle;
    }
}

