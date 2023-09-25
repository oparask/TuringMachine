package model;

//L’interface Shape représente une forme et définit les comportements attendus par toute
//forme
public interface Shape {
    void move(double dx, double dy); //permettant de déplacer une forme ;

    boolean isInside(Point p); //retournant vrai si le point donné se trouve à l’intérieur de la forme et faux sinon

    char getColor(); //retournant un caractère d’affichage, par exemple le caractère ’c’

    void setColor(char color); // modifiant la couleur de la forme ;


}

