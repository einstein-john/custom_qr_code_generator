public class Main {
  public static void main(String[] args) {
    for (int a = 0; a <4; a++){
      new QRCodeGenerator("Number "+a,"qrcode"+a+".png");
    }
  }
}
