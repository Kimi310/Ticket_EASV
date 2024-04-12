package GUI.Controller;

import BE.Ticket;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TicketPrintViewController {
    @FXML
    public HBox ticketNHbox;
    private Stage stage;
    private TicketNController ticketNController;
    private Ticket ticket;
    private int currentIndex = 0;
    private String ticketType;
    private String path = "src/GUI/View/Images/qrcode.png";
    private String charset = "UTF-8";
    private Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTicket(Ticket ticket, String ticketType) throws IOException, WriterException {
        this.ticket = ticket;
        WritableImage qrImage = generateQRcode(ticket.getSerialNumber(), 130, 130);
        displayTicket();
        ticketNController.ticketTypeLabel.setText(ticketType);
        ticketNController.qrCodeImageView.setImage(qrImage);
    }

    private void displayTicket() {
        ticketNHbox.getChildren().clear();
        addTicketToPrint(ticket);
    }

    private void addTicketToPrint(Ticket ticket) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/Ticket.fxml"));
        try {
            ticketNHbox.getChildren().add(loader.load());
            ticketNController = loader.getController();
            ticketNController.setNewTicket(ticket.getTicketName(), ticket.getTicketEmail(), ticket.getTicketPrice(), ticket.getSerialNumber(), ticket.getEventTime(), ticket.getEventLocation(), ticket.getEventName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCurrentTicket(ActionEvent actionEvent) {

            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(stage.getOwner())) {
                boolean success = job.printPage(ticketNHbox);
                if (success) {
                    job.endJob();
                }
            }

    }


    public static WritableImage generateQRcode(String data, int h, int w) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, w, h);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
        return SwingFXUtils.toFXImage(image, null);
    }



}
