package imagemanage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author ThiagoAugustus
 */
public class Imagem {
    
    private final String  format;
    private String        imagemName;
    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private int[][]       newImage;
    
    public Imagem()
    {
        this.format = "jpg";
    }
    
    public Imagem loadImage(String name)
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
                Random gerador = new Random();
                    
            for (int x = 0; x < this.inputImage.getWidth(); x++) {
                
                for (int y = 0; y < this.inputImage.getHeight(); y++) {
                    
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x, y);
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue  = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red   = 0x0000ff & (rgb >> 16);
                    
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
    
    public Imagem grainImage(String name, int max, int marge)
    {
        try {
            
            this.outputImage = new BufferedImage( 
                this.inputImage.getWidth(), 
                this.inputImage.getHeight(), 
                BufferedImage.TYPE_INT_RGB);
                Random gerador = new Random();
                    
            for (int x = 0; x < this.inputImage.getWidth(); x++) {
                
                for (int y = 0; y < this.inputImage.getHeight(); y++) {
                    
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x, y);
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue  = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red   = 0x0000ff & (rgb >> 16);
                    
                    // Cria uma escala de cor
                    int lum = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                    
                    if(gerador.nextInt(max) > marge) {
                        this.outputImage.setRGB(x, y, 255 | (255 << 8) | (255 << 16));                        
                    } else {
                        // Grava os pixels na imagem de saida
                        this.outputImage.setRGB(x, y, blue | (green << 8) | (red << 16));
                    }
                    
                }
            }
            
            // Grava a imagem no disco
            ImageIO.write(this.outputImage, "jpg", new File(name));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return this;
    }
    
    public Imagem grayImage(String name)
    {        
        try {
            
            this.outputImage = new BufferedImage( 
                this.inputImage.getWidth(), 
                this.inputImage.getHeight(), 
                BufferedImage.TYPE_INT_RGB);
                Random gerador = new Random();
                    
            for (int x = 0; x < this.inputImage.getWidth(); x++) {
                
                for (int y = 0; y < this.inputImage.getHeight(); y++) {
                    
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x, y);
                    
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue  = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red   = 0x0000ff & (rgb >> 16);
                    
                    // Cria uma escala de cor
                    int lum = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                   
                    // Grava os pixels na imagem de saida
                    this.outputImage.setRGB(x, y, lum | (lum << 8) | (lum << 16));
                }
            }
            
            // Grava a imagem no disco
            ImageIO.write(this.outputImage, "jpg", new File(name));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return this;
    }
    
    public Imagem binarizacao(String name, int alpha)
    {        
        try {
            
            this.outputImage = new BufferedImage( 
                this.inputImage.getWidth(), 
                this.inputImage.getHeight(), 
                BufferedImage.TYPE_INT_RGB);
                Random gerador = new Random();
                    
            for (int x = 0; x < this.inputImage.getWidth(); x++) {
                
                for (int y = 0; y < this.inputImage.getHeight(); y++) {
                    
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x, y);
                    
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue  = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red   = 0x0000ff & (rgb >> 16);
                    
                    if((blue+green+red)/3 > alpha){
                        // Grava os pixels na imagem de saida
                        this.outputImage.setRGB(x, y, 255 | (255 << 8) | (255 << 16));
                    } else {
                        // Grava os pixels na imagem de saida
                        this.outputImage.setRGB(x, y, 0 | (0 << 8) | (0 << 16));
                    }
                }
            }
            
            // Grava a imagem no disco
            ImageIO.write(this.outputImage, "jpg", new File(name));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return this;
    }
    
    public Imagem cropImage(String name, int width, int height)
    {
        
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
    
    public Imagem zoomInQuadrado(String name){
        
        try {
            
            this.outputImage = new BufferedImage( 
                this.inputImage.getWidth() * 4, 
                this.inputImage.getHeight() * 4, 
                BufferedImage.TYPE_INT_RGB);
                Random gerador = new Random();
                    
            for (int x = 0; x < this.outputImage.getWidth(); x+=2) {
                
                for (int y = 0; y < this.outputImage.getHeight(); y+=2) {
                    
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x/4, y/4);
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue  = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red   = 0x0000ff & (rgb >> 16);
                    // Grava os pixels na imagem de saida
                    this.outputImage.setRGB(x, y, blue | (green << 8) | (red << 16));
                    this.outputImage.setRGB(x+1, y, blue | (green << 8) | (red << 16));
                    this.outputImage.setRGB(x, y+1, blue | (green << 8) | (red << 16));
                    this.outputImage.setRGB(x+1, y+1, blue | (green << 8) | (red << 16));
                }
            }
            
            // Grava a imagem no disco
            ImageIO.write(this.outputImage, "jpg", new File(name));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return this;        
    }
    
    public Imagem zoomInLinear(String name){
        
        try {
            
            this.outputImage = new BufferedImage( 
                this.inputImage.getWidth()*2-1, 
                this.inputImage.getHeight()*2-1, 
                BufferedImage.TYPE_INT_RGB);
                Random gerador = new Random();
            
            for (int x = 0; x < this.inputImage.getWidth(); x++) {

                for (int y = 0; y < this.inputImage.getHeight(); y++) {
                    
                    // Recupera o número representativo do binário para o pixel indicado
                    int rgb = this.inputImage.getRGB(x, y);
                    // Separa as cores binárias em uma escala de um inteiro de 255
                    int blue  = 0x0000ff & rgb;
                    int green = 0x0000ff & (rgb >> 8);
                    int red   = 0x0000ff & (rgb >> 16);
                    
                    if(y%2!=0){
                       this.outputImage.setRGB(x, y, 255 | (255 << 8) | (255 << 16)); 
                       this.outputImage.setRGB(this.inputImage.getWidth()+x-1, y, 255 | (255 << 8) | (255 << 16));  
                       this.outputImage.setRGB(x, this.inputImage.getHeight()+y-1, 255 | (255 << 8) | (255 << 16));  
                       this.outputImage.setRGB(this.inputImage.getWidth()+x-1, this.inputImage.getHeight()+y-1, 255 | (255 << 8) | (255 << 16));  
                    }
                    else if(x%2!=0){
                       this.outputImage.setRGB(x, y, 255 | (255 << 8) | (255 << 16)); 
//                       this.outputImage.setRGB(x, this.inputImage.getHeight()+y-1, 255 | (255 << 8) | (255 << 16));
                       this.outputImage.setRGB(this.inputImage.getWidth()+x-1, y, 255 | (255 << 8) | (255 << 16));    
//                       this.outputImage.setRGB(this.inputImage.getWidth()+x-1, this.inputImage.getHeight()+y-1, 255 | (255 << 8) | (255 << 16)); 
                    }
//                    else if (x%2!=0 && y%2!=0){
//                        this.outputImage.setRGB(((x*2)-1), ((y*2)-1), 255 | (255 << 8) | (255 << 16));   
//                    }
                        
                    // Grava os pixels na imagem de saida
                    this.outputImage.setRGB(x*2, y*2, blue | (green << 8) | (red << 16));

                }
            }

            // Grava a imagem no disco
            ImageIO.write(this.outputImage, "jpg", new File(name));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return this;        
    }
    
}
