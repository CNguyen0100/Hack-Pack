/*
Computational Geometry
 */

/**
 *
 * @author Cuong Nguyen
 */
import java.util.*;
public class geometry {
    static final double INF = 1e100;
    static final double EPS = 1e-12;
    
    static double dot(PT p,PT q){return p.x*q.x+p.y*q.y;}
    static double dist2(PT p, PT q){return dot(p.minus(q),p.minus(q));}
    static double cross(PT p, PT q){return p.x*q.y-p.y*q.x;}
    
    //determine if lines ab and cd are // or colinear
    static boolean LinesParallel(PT a, PT b, PT c, PT d){
        return (Math.abs(cross(b.minus(a), c.minus(d)))< EPS);
    }
    static boolean LinesCollinear(PT a, PT b, PT c, PT d){
        return (LinesParallel(a, b, c, d) &&
                Math.abs(cross(a.minus(b),a.minus(c)))<EPS
                && Math.abs(cross(c.minus(d), c.minus(a)))<EPS
                );
    }
    
    //determine if line segment from a to b intersects with line cd
    static boolean SegmentsIntersect(PT a, PT b, PT c, PT d){
        if(LinesCollinear(a, b, c, d)){
            if (dist2(a,c) < EPS || dist2(a,d) < EPS ||
				dist2(b,c)< EPS || dist2(b,d)<EPS)
				return true;
			if(dot(c.minus(a), c.minus(b)) >0 && dot(d.minus(a),d.minus(b))>0
				&& dot(c.minus(b),d.minus(b))>0)
				return false;
			return true;
        }
        if(cross(d.minus(a),b.minus(a))*cross(c.minus(a), b.minus(a) )>0) return false;
        if(cross(a.minus(c),d.minus(c))*cross(b.minus(c), d.minus(c) )>0) return false;
		
        return true;
    }
    
    
}
class PT{
    double x, y;
    PT(){}
    PT(double x, double y){this.x =x; this.y=y;}
    PT add(PT p){return new PT(x+p.x,y+p.y);}
    PT minus(PT p){return new PT(x-p.x,y-p.y);}
    PT mul(double c){return new PT(x*c, y*c);}
    PT div(double c){return new PT(x/c, y/c);}
}

