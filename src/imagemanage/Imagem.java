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

    private String imagemName;
    private final String format;
    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private int[][] newImage;
    
    public Imagem()
    {
        this.format = "jpg";
    }
    
    public Imagem setPath(String name)
    {
        // Guarda o nome do arquivo
        this.imagemName = name;
        
        try {
            // Faz a leitura da imagem
            this.inputImage = ImageIO.read(new File("src/img/"+this.imagemName));
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return this;
    }
    
    public Imagem writeImage(String name)
    {        
        try {
            
            this.outputImage = new BufferedImage( 
                this.inputImage.getWidth(), 
                this.inputImage.getHeight(), 
                BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < this.inputImage.getWidth(); x++) {
                
                for (int y = 0; y < this.inputImage.getHeight(); y++) {
                    
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x, y);
                    
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red = 0x0000ff & (rgb >> 16);
                    
                    // Cria uma escala de cor
                    int lum = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                    
                    // Grava os pixels na imagem de saida
                    this.outputImage.setRGB(x, y, blue | (green << 8) | (red << 16));
                }
            }
            
            // Grava a imagem no disco
            ImageIO.write(this.outputImage, "jpg", new File(name));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return this;
    }
    
    public Imagem writeImage(String name, int width, int height){
        
        try {
            
            this.outputImage = new BufferedImage( 
                width, 
                height, 
                BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width; x++) {
                
                for (int y = 0; y < height; y++) {
                    
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x, y);
                    
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red = 0x0000ff & (rgb >> 16);
                    
                    this.outputImage.setRGB(x, y, blue | (green << 8) | (red << 16));
                }
            }
            
            ImageIO.write(this.outputImage, "jpg", new File(name));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
    
}
