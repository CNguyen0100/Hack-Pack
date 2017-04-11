/*
Computational Geometry
 */

/**
 *
 * @author Cuong Nguyen
 */
import java.util.*;
class PT{
    double x, y;
    PT(){}
    PT(double x, double y){this.x =x; this.y=y;}
    PT add(PT p){return new PT(x+p.x,y+p.y);}
    PT minus(PT p){return new PT(x-p.x,y-p.y);}
    PT mul(double c){return new PT(x*c, y*c);}
    PT div(double c){return new PT(x/c, y/c);}
}
public class geometry {
    static final double INF = 1e100;
    static final double EPS = 1e-12;
    
    static double dot(PT p,PT q){return p.x*q.x+p.y*q.y;}
    static double dist2(PT p, PT q){return dot(p.minus(q),p.minus(q));}
    static double cross(PT p, PT q){return p.x*q.y-p.y*q.x;}
    
	/**LINE**/
    //determine if lines-segment ab and cd are // or colinear
    static boolean LinesParallel(PT a, PT b, PT c, PT d){
        return (Math.abs(cross(b.minus(a), c.minus(d)))< EPS);
    }
    static boolean LinesCollinear(PT a, PT b, PT c, PT d){
        return (LinesParallel(a, b, c, d) &&
                Math.abs(cross(a.minus(b),a.minus(c)))<EPS
                && Math.abs(cross(c.minus(d), c.minus(a)))<EPS
                );
    }
    
    //determine if line segment ab intersects with line-segment cd
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
    
	//compute intersection of line through a and b with line and line through c and d
	//assume that point exist
	PT ComputeLineIntersection(PT a, PT b, PT c ,PT d){
		b= b.minus(a);
		d = c.minus(d);
		c = c.minus(a);
		assert dot(b,b)> EPS && dot(d,d)>EPS;
		return a.add(b.mul(cross(c,d)/cross(b,d)));
	}
	
	PT RotateCCW90(PT p){return new PT(-p.y,p.x);}
	PT RotateCW90(PT p){return new PT(p.y,-p.x);}
	PT RotateCCW(PT p,double t){
		return new PT(p.x*Math.cos(t)-p.y*Math.sin(t), p.x*Math.sin(t)+p.y*Math.cos(t));
	}
	
	//project point c onto line through a and b
	//assuming a!=  b
	PT ProjectPointLine(PT a, PT b, PT c){
		return a.add(b.minus(a)*dot(c.minus(a))/dot(b.minus(a),b.minus(a)));
	}
	
	//project point c onto line through a and b
	PT ProjectPointSegment(PT a, PT b, PT c){
		double r =  dot(b.minus(a), b.minus(a));
		if(Math.abs(r)< EPS) return a;
		r = dot(c.minus(a), b.minus(a));
		if(r<0)return a;
		if(r>1)return b;
		return a.add((b.minus(a)).mul(r));
	}
    
	//Compute Distance from c to segment between a and b
	double DistancePointSegment(PT a, PT b, PT c){
		return Math.sqrt(dist2(c, ProjectPointSegment(a,b,c)));
	}
	
	/**Polygon**/
	
}


