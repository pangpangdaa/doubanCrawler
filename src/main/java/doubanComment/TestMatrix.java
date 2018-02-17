package doubanComment;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;
import org.ujmp.core.calculation.Calculation.Ret;

public class TestMatrix {
	
	public static void main(String[] args){
		Matrix dense=DenseMatrix.Factory.zeros(4,4);
		dense.setAsDouble(5.0, 2, 3);

		// set some other values
		dense.setAsDouble(1.0, 0, 0);
		dense.setAsDouble(3.0, 1, 1);
		dense.setAsDouble(4.0, 2, 2);
		dense.setAsDouble(-2.0, 3, 3);
		dense.setAsDouble(-2.0, 1, 3);
		Matrix similary=dense.cosineSimilarity(Ret.NEW, true);
		System.out.println(similary);
	}

}
