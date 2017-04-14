class pt {

	public double x;
	public double y;
	public double z;

	public pt(double myx, double myy, double myz) {
		x = myx;
		y = myy;
		z = myz;
	}

	// Returns the vector from this to to.
	public vect getVect(pt to) {
		return new vect(to.x-x, to.y-y, to.z-z);
	}
}

class vect {

	public double x;
	public double y;
	public double z;

	public vect(double myx, double myy, double myz) {
		x = myx;
		y = myy;
		z = myz;
	}

	// Returns the cross product this x other.
	public vect cross(vect other) {
		return new vect(y*other.z-other.y*z, z*other.x-other.z*x, x*other.y-other.x*y);
	}

	// Returns the magnitude of this vect.
	public double mag() {
		return Math.sqrt(x*x+y*y+z*z);
	}
}

class line {

	public pt start;
	public pt end;
	public vect dir;

	public line(pt s, pt e) {
		start = s;
		end = e;
		dir = start.getVect(end);
	}

	// Returns the point that corresponds to parameter t on this line.
	public pt getPt(double t) {
		return new pt(start.x+dir.x*t, start.y+dir.y*t, start.z+dir.z*t);
	}
}

class plane {

	public pt p1;
	public pt p2;
	public pt p3;
	public vect normal;
	public double d;

	public plane(pt a, pt b, pt c) {
		p1 = a;
		p2 = b;
		p3 = c;
		vect v1 = p1.getVect(p2);
		vect v2 = p1.getVect(p3);
		normal = v1.cross(v2);

		// We get D by plugging in one of the plane points into the equation for the plane.
		d = normal.x*p1.x + normal.y*p1.y + normal.z*p1.z;
	}

	public pt intersect(line myLine) {

		// Get coefficient of parameter t in solution - cull out intersections that aren't points.
		double tCoeff = normal.x*myLine.dir.x + normal.y*myLine.dir.y + normal.z*myLine.dir.z;
		if (Math.abs(tCoeff) < 1e-9) return null;

		// Solve for the parameter.
		double rhs = d - normal.x*myLine.start.x - normal.y*myLine.start.y - normal.z*myLine.start.z;

		// Return the corresponding point.
		return myLine.getPt(rhs/tCoeff);
	}

	// Returns true iff p is on this plane.
	public boolean onPlane(pt p) {
		return normal.x*p.x + normal.y*p.y + normal.z*p.z == d;
	}
}