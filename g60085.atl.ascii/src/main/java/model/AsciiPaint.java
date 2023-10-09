package model;

//est la façade du modèle et contient les méthodes permettant de modifier le
//modèle : ajouter une forme, bouger une forme, changer la couleur, etc. La façade contient
//aussi les méthodes permettant de récupérer les informations nécessaires à l’affichage
public class AsciiPaint {
    private Drawing drawing;

    public AsciiPaint() {
        drawing = new Drawing();
    }

    public AsciiPaint(int width, int height) {
        drawing = new Drawing(width, height);
    }

    //je l'ai rajuté moi
    public Drawing getDrawing() {
        return drawing;
    }

    public void newCircle(int x, int y, double radius, char color) {
        drawing.addShape(new Circle(new Point(x, y), radius, color));
    }

    public void newRectangle(int x, int y, double width, double height, char color) {
        drawing.addShape(new Rectangle(new Point(x, y), width, height, color));
    }

    public void newSquare(int x, int y, double side, char color) {
        drawing.addShape(new Square(new Point(x, y), side, color));
    }

    public String asASCII() {
        String colorShapes = "";
        for (int y = 0; y < drawing.getHeight(); y++) { // Parcourir la hauteur (verticale)
            for (int x = 0; x < drawing.getWidth(); x++) { // Parcourir la largeur (horizontale)
                Shape shape = drawing.getShapeAt(new Point(x, y));
                if (shape != null) {
                    colorShapes += shape.getColor();
                } else {
                    colorShapes += " ";
                }
            }
        }
        return colorShapes;
    }
}
