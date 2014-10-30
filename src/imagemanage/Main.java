/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanage;

/**
 *
 * @author ThiagoAugustus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Imagem img = new Imagem();
        img.loadImage("image.jpg")
//                .grayImage("gray.jpg")
//                .grainImage("grain.jpg", 10000, 9900)
//                .binarizacao("bipolarizacao.jpg", 100)
//                .zoomInQuadrado("zoomInQuadrado.jpg")
                .zoomInLinear("zoomInLinear.jpg");
    }
    
}
