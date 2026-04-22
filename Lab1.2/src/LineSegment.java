public class LineSegment {
    private double x1, y1, x2, y2;

    public LineSegment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double getAngleWithOx() {
        double angleRad = Math.atan2(y2 - y1, x2 - x1);
        return (Math.toDegrees(angleRad) + 360) % 360;
    }

    public int getAngleKey() {
        return (int) getAngleWithOx();
    }

    @Override
    public String toString() {
        return String.format("Вiдрiзок [A(%.1f; %.1f) -> B(%.1f; %.1f)] | Довжина: %.2f | Кут з OX: %.1f°", 
                              x1, y1, x2, y2, getLength(), getAngleWithOx());
    }
    
}
