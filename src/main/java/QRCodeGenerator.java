import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeGenerator {

  private static  String QR_CODE_IMAGE_PATH = "QR_codes\\qrcode.png";


    String text;
    int width = 350;
    int height = 350;
    String format = "png";

  public QRCodeGenerator(String input, String fName){
    QR_CODE_IMAGE_PATH = fName;
    text = input;
    try {
      generateQRCode(text, width, height, format);
      System.out.println("QR Code generated successfully.");
    } catch (WriterException | IOException e) {
      System.out.println("Could not generate QR Code: " + e.getMessage());
    }
  }


  private static void generateQRCode(String text, int width, int height, String format)
    throws WriterException, IOException {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, getQRCodeHints());

    BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        qrCodeImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
      }
    }

    File qrCodeFile = new File(QR_CODE_IMAGE_PATH);
    ImageIO.write(qrCodeImage, format, qrCodeFile);
  }

  private static java.util.Map<EncodeHintType, Object> getQRCodeHints() {
    java.util.Map<EncodeHintType, Object> hints = new java.util.HashMap<>();
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    return hints;
  }
}
