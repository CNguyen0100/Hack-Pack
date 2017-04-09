/**
 * Defines a rectangle and common operations over it.
 *
 * @author Sinisha Djukic
 * @version 1.0.0 (2001)
 */
public class Rectangle {

    /**The x coordinate of the top-left corner.*/
    public int x;
    /**The y coordinate of the top-left corner.*/
    public int y;
    /**The width of the rectangle.*/
    public int width;
    /**The height of the rectangle.*/
    public int height;

    /**
     * Create a new <code>Rectangle</code> given its boundary parameters.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param width rectangle width
     * @param height recrangle height
     */
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if the first rectangle contains the second.
     *
     * @param rectA first rectangle
     * @param rectB second rectangle
     * @return <code>true</code> if <code>rectA</code> contains <code>rectB</code>
     */
    public static final boolean contains(Rectangle rectA, Rectangle rectB) {
        return (rectB.x >= rectA.x) && (rectB.y >= rectA.y) &&
                (rectB.x + rectB.width <= rectA.x + rectA.width) && (rectB.y + rectB.height <= rectA.y + rectA.height);
    }

    /**
     * Checks if two rectangles intersect
     *
     * @param rectA first rectangle
     * @param rectB second rectangle
     * @return <code>true</code> if <code>rectA</code> and <code>rectB</code> intersect
     */
    public static final boolean intersects(Rectangle rectA, Rectangle rectB) {
        return !((rectB.x + rectB.width <= rectA.x) ||
                (rectB.y + rectB.height <= rectA.y) ||
                (rectB.x >= rectA.x + rectA.width) ||
                (rectB.y >= rectA.y + rectA.height));
    }

    /**
     * Computes the difference of two rectangles. Difference of two rectangles
     * can produce a maximum of four rectangles. If the two rectangles do not intersect
     * a zero-length array is returned.
     *
     * @param rectA first rectangle
     * @param rectB second rectangle
     * @return non-null array of <code>Rectangle</code>s, with length zero to four
     */
    public static Rectangle[] difference(Rectangle rectA, Rectangle rectB) {
        if (rectB == null || !intersects(rectA, rectB) || contains(rectB, rectA))
            return new Rectangle[0];

        Rectangle result[] = null;
        Rectangle top = null, bottom = null, left = null, right = null;
        int rectCount = 0;

        //compute the top rectangle
        int raHeight = rectB.y - rectA.y;
        if(raHeight>0) {
            top = new Rectangle(rectA.x, rectA.y, rectA.width, raHeight);
            rectCount++;
        }

        //compute the bottom rectangle
        int rbY = rectB.y + rectB.height;
        int rbHeight = rectA.height - (rbY - rectA.y);
        if ( rbHeight > 0 && rbY < rectA.y + rectA.height ) {
            bottom = new Rectangle(rectA.x, rbY, rectA.width, rbHeight);
            rectCount++;
        }

        int rectAYH = rectA.y+rectA.height;
        int y1 = rectB.y > rectA.y ? rectB.y : rectA.y;
        int y2 = rbY < rectAYH ? rbY : rectAYH;
        int rcHeight = y2 - y1;

        //compute the left rectangle
        int rcWidth = rectB.x - rectA.x;
        if ( rcWidth > 0 && rcHeight > 0 ) {
            left = new Rectangle(rectA.x, y1, rcWidth, rcHeight);
            rectCount++;
        }

        //compute the right rectangle
        int rbX = rectB.x + rectB.width;
        int rdWidth = rectA.width - (rbX - rectA.x);
        if ( rdWidth > 0 ) {
            right = new Rectangle(rbX, y1, rdWidth, rcHeight);
            rectCount++;
        }

        result = new Rectangle[rectCount];
        rectCount = 0;
        if(top != null)
            result[rectCount++] = top;
        if(bottom != null)
            result[rectCount++] = bottom;
        if(left != null)
            result[rectCount++] = left;
        if(right != null)
            result[rectCount++] = right;
        return result;
    }

}