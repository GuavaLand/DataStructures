public class NBody {

    public static double readRadius(String str){
        In in = new In(str);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Body[] readBodies(String str){

        In in = new In(str);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] bs = new Body[N];
        for (int i = 0; i < N; i++){
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xvel = in.readDouble();
            double yvel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            bs[i]  = new Body(xpos,ypos,xvel,yvel,mass,img);
        }
        return bs;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Body[] bodies = NBody.readBodies(filename);
        double R = NBody.readRadius(filename);

        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-R, R);

        for (double t = 0; t < T; t = t+dt){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int i = 0; i<bodies.length; i++){
                Body b = bodies[i];
                xForces[i] = b.calcNetForceExertedByX(bodies);
                yForces[i] = b.calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i<bodies.length; i++){
                Body b = bodies[i];
                b.update(dt,xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b:bodies){
                b.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }

}
