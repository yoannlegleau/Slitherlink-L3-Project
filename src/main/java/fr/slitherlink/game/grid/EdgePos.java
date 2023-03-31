package fr.slitherlink.game.grid;

/**
 * @author Bruneau Antoine
 * @version 1, 12/03/2023
 */
public class EdgePos extends Coordinates {
    private int pos;

    public EdgePos(int pos, int y, int x) {
        super(y, x);
        this.pos = pos;
    }

    public int getPosition() {
        return pos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EdgePos other = (EdgePos) obj;
        if (this.pos == other.pos && this.x == other.x && this.y == other.y) {
            return true;
        }
        
        return false;
    }


}
