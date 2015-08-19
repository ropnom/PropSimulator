package eetac.model.basicstructure;

public class BlockRelations {
	/*
	 * This class provides the relations between simulation block variables
	 */

	// Elements in relation
	protected SimulationBlock origin;
	protected SimulationBlock destination;
	protected short origin_variable_num;
	protected short destination_variable_num;

	// is threre any constants definend by user?
	protected boolean cte_origen = false;
	protected boolean cte_destination = false;

	public BlockRelations() {

	}

	public BlockRelations(SimulationBlock origin, SimulationBlock destination, short origin_variable_num, short destination_variable_num, boolean cte_origen, boolean cte_destination) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.origin_variable_num = origin_variable_num;
		this.destination_variable_num = destination_variable_num;
		this.cte_origen = cte_origen;
		this.cte_destination = cte_destination;
	}

	//copy contructor
	public BlockRelations(BlockRelations a) {

		this.origin = new SimulationBlock(a.getOrigin());
		this.destination = new SimulationBlock(a.getDestination());
		this.origin_variable_num = a.getOrigin_variable_num();
		this.destination_variable_num = a.getDestination_variable_num();
		this.cte_origen = a.isCte_destination();
		this.cte_destination = a.isCte_destination();
	}

	protected void UpdateValues() {
		/*
		 * This method set the same value in the two variable relationated, if
		 * one is a constant set in the other, if there isn't a constant
		 * calculate the mean o both.
		 */

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
			value = (destination.getMatrices().getX_equations()[0][destination_variable_num] + origin.getMatrices().getX_equations()[0][origin_variable_num]) / 2;

			origin.setvariable(value, origin_variable_num);
			origin.getMatrices().getX_equations()[origin_variable_num][0] = value;
			destination.setvariable(value, destination_variable_num);
			destination.getMatrices().getX_equations()[destination_variable_num][0] = value;

		}
	}

	public SimulationBlock getOrigin() {
		return origin;
	}

	public void setOrigin(SimulationBlock origin) {
		this.origin = origin;
	}

	public SimulationBlock getDestination() {
		return destination;
	}

	public void setDestination(SimulationBlock destination) {
		this.destination = destination;
	}

	public short getOrigin_variable_num() {
		return origin_variable_num;
	}

	public void setOrigin_variable_num(short origin_variable_num) {
		this.origin_variable_num = origin_variable_num;
	}

	public short getDestination_variable_num() {
		return destination_variable_num;
	}

	public void setDestination_variable_num(short destination_variable_num) {
		this.destination_variable_num = destination_variable_num;
	}

	public boolean isCte_origen() {
		return cte_origen;
	}

	public void setCte_origen(boolean cte_origen) {
		this.cte_origen = cte_origen;
	}

	public boolean isCte_destination() {
		return cte_destination;
	}

	public void setCte_destination(boolean cte_destination) {
		this.cte_destination = cte_destination;
	}

}
