/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author ThiagoAugustus
 */
public class Imagem {

    private int x;
    private int y;
    private String path;
    private final String format;
    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private int[][] newImage;
    
    public Imagem()
    {
        this.format = "jpg";
    }
    
    public Imagem setPath(String path)
    {
        // Guarda o caminho do arquivo
        this.path = path;
        
        try {
            
            // Faz a leitura da imagem
            this.inputImage = ImageIO.read(new File(this.path));
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return this;
    }
    
    public Imagem setVector(int y, int x)
    {
        // Armazena o tamanho da sub-matriz
        this.y = y;
        this.x = x;
        
        try {
            
            this.outputImage = new BufferedImage( 
                this.inputImage.getWidth(), 
                this.inputImage.getHeight(), 
                BufferedImage.TYPE_INT_RGB);

            for (int x2 = 0; x2 < this.inputImage.getWidth(); x2++) {
                for (int y2 = 0; y2 < this.inputImage.getHeight(); y2++) {
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x2, y2);
                    
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red = 0x0000ff & (rgb >> 16);
                    
                    // Cria uma cor
                    int lum = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                    this.outputImage.setRGB(x2, y2, lum | (lum << 8) | (lum << 16));
                }
            }
            
            ImageIO.write(this.outputImage, "jpg", new File("saida.jpg"));
        
        } catch (IOException e) {
        
        }
        
        return this;
    }
    
    
}
