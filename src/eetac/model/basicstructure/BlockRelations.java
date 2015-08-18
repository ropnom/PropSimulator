package eetac.model.basicstructure;

public class BlockRelations {

	protected SimulationBlock origin;
	protected SimulationBlock destination;
	protected short origin_variable_num;
	protected short destination_variable_num;

	protected boolean cte_origen = false;
	protected boolean cte_destination = false;

	protected void UpdateValues() {

		// short origin_variable = origin.getInitnum()+origin_variable_num
		double value = 0;

		if (cte_origen) {
			// Put origin value on destination
			value = origin.getMatrices().getX_equations()[0][origin_variable_num];

			destination.setvariable(value, destination_variable_num);
			destination.getMatrices().getX_equations()[destination_variable_num][0] = value;

		} else if (cte_destination) {
			
			// Put destination value on origin
			value = destination.getMatrices().getX_equations()[0][destination_variable_num];

			origin.setvariable(value, origin_variable_num);
			origin.getMatrices().getX_equations()[origin_variable_num][0] = value;

		} else {
			
			// Put mean value on destination and origin
			value = (destination.getMatrices().getX_equations()[0][destination_variable_num] + origin.getMatrices().getX_equations()[0][origin_variable_num])/2;
			
			origin.setvariable(value, origin_variable_num);
			origin.getMatrices().getX_equations()[origin_variable_num][0] = value;
			destination.setvariable(value, destination_variable_num);
			destination.getMatrices().getX_equations()[destination_variable_num][0] = value;

		}
	}
	
	protected void FixMatrix(){
		
		//Fix X component matrix
	}

}
