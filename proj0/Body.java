public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double distance = Math.pow(Math.pow(this.xxPos-b.xxPos,2)+Math.pow(this.yyPos-b.yyPos,2),0.5);
        return distance;

    }

    public double calcForceExertedBy(Body b) {
        double distance = this.calcDistance(b);
        double force = 6.67e-11*this.mass*b.mass/Math.pow(distance,2);
        return force;
    }

    public double calcForceExertedByX(Body b) {
        double distance = this.calcDistance(b);
        double force = this.calcForceExertedBy(b);
        return (b.xxPos-this.xxPos)*force/distance;

    }

    public double calcForceExertedByY(Body b) {
        double distance = this.calcDistance(b);
        double force = this.calcForceExertedBy(b);
        return (b.yyPos-this.yyPos)*force/distance;
    }

    public double calcNetForceExertedByX(Body[] allBodies){
        double forcex = 0;
        for (int i=0; i < allBodies.length; i++){
            Body b = allBodies[i];
            if (this.equals(b)){
                continue;
            }
            forcex += this.calcForceExertedByX(b);
        }
        return forcex;
    }

    public double calcNetForceExertedByY(Body[] allBodies){
        double forcey = 0;
        for (int i=0; i < allBodies.length; i++){
            Body b = allBodies[i];
            if (this.equals(b)){
                continue;
            }
            forcey += this.calcForceExertedByY(b);
        }
        return forcey;
    }

    public void update(double time, double forcex, double forcey) {
        double ax = forcex/this.mass;
        double ay = forcey/this.mass;

        this.xxVel += time*ax;
        this.yyVel += time*ay;

        this.xxPos += time*this.xxVel;
        this.yyPos += time*this.yyVel;

    }

    public void draw(){

        String img = "images/"+this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, img);

    }
}
