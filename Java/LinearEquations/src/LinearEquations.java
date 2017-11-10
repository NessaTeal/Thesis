import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinearEquations {

	public static void main(String[] args) {
		
		List<List<Double>> matrix = new ArrayList<>();
		
		List<Double> row0 = new ArrayList<>(Arrays.asList(1.0,1.0,1.0));
		List<Double> row1 = new ArrayList<>(Arrays.asList(0.0,2.0,5.0));
		List<Double> row2 = new ArrayList<>(Arrays.asList(2.0,5.0,-1.0));
		
		matrix.add(row0);
		matrix.add(row1);
		matrix.add(row2);

		List<Double> vector = Arrays.asList(new Double[] {6.0,-4.0,27.0});
		
		double determinant = getDeterminant(matrix);
		List<List<Double>> adjugate = getAdjugate(matrix);
		List<Double> multiplied = multiplyMatrixAndVector(adjugate, vector);
		List<Double> answer = multiplied.stream().map(x -> x / determinant).collect(Collectors.toList());
		
		System.out.println(answer);
	}
	
	public static double getMinor(int i, int j, List<List<Double>> matrix) {
		
		List<List<Double>> minorMatrix = new ArrayList<>();
		
		for(List<Double> row : matrix) {
			minorMatrix.add(new ArrayList<Double>(row));
		}
		
		minorMatrix.remove(i);
		
		for(List<Double> row : minorMatrix) {
			row.remove(j);
		}
		
		return Math.pow(-1, i + j) * getDeterminant(minorMatrix);
	}
	
	public static double getDeterminant(List<List<Double>> matrix) {
		if(matrix.size() == 2) {
			double a = matrix.get(0).get(0);
			double b = matrix.get(0).get(1);
			double c = matrix.get(1).get(0);
			double d = matrix.get(1).get(1);
			
			return a * d - b * c;
		} else {
			double sum = 0;
			
			for(int y = 0; y < matrix.size(); y++) {
				sum += matrix.get(0).get(y) * getMinor(0, y, matrix);
			}
			
			return sum;
		}
	}

	public static List<List<Double>> getAdjugate(List<List<Double>> matrix) {
		
		List<List<Double>> adjugate = new ArrayList<>();
		
		for(int y = 0; y < matrix.size(); y++) {
			
			List<Double> adjugateRow = new ArrayList<>();
			
			for(int x = 0; x < matrix.size(); x++) {
				adjugateRow.add(getMinor(x, y, matrix));
			}
			
			adjugate.add(adjugateRow);
		}
		
		return adjugate;
	}
	
	public static List<Double> multiplyMatrixAndVector(List<List<Double>> matrix, List<Double> vector) {
		List<Double> answer = new ArrayList<>();
		
		for(List<Double> row : matrix) {
			
			double sum = 0;
			
			for(int i = 0; i < row.size(); i++) {
				sum += row.get(i) * vector.get(i);
			}
			
			answer.add(sum);
		}
		
		return answer;
	}
}
