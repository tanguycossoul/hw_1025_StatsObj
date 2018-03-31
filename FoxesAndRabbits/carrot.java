
import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a . Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kolling. Modified by David Dobervich
 *         2007-2013
 * @version 2006.03.30
 */
public class carrot implements Serializable {
	private static final int REPRODUCTION_AGE = 5;

	// The age to which a  can live.
	private static final int MAX_AGE = 30;

	// The likelihood of a  breeding.
	private static final double REPRODUCTION_PROBABILITY = 0.8;

	// The maximum number of births.
	private static final int MAX_REPRODUCTION_SPEED = 5;

	// A shared random number generator to control breeding.
	private static final Random rand = new Random();

	// Individual characteristics (instance fields).

	// The 's age.
	private int age;

	// Whether the  is alive or not.
	private boolean alive;

	// The 's position
	private Location location;

	/**
	 * Create a new . A  may be created with age zero (a new born) or
	 * with a random age.
	 * 
	 * @param randomAge
	 *            If true, the  will have a random age.
	 */
	public void Rabbit(boolean randomAge) {
		age = 0;
		alive = true;
		if (randomAge) {
			age = rand.nextInt(MAX_AGE);
		}
	}

	/**
	 * This is what the  does most of the time - it runs around. Sometimes it
	 * will breed or die of old age.
	 * 
	 * @param updatedField
	 *            The field to transfer to.
	 * @param newRabbits
	 *            A list to add newly born s to.
	 */
	public void spread(Field updatedField, List<carrot> newcarrots) {
		incrementAge();
		if (alive) {
			int births = breproduce() ;
			for (int b = 0; b < births; b++) {
				Rabbit newRabbit = new Rabbit(false);
				newcarrots.add((carrot) newcarrots);
				Location loc = updatedField.randomAdjacentLocation(location);
				((carrot) newcarrots).setLocation(loc);
				updatedField.put(newcarrots, loc);
			}
			Location newLocation = updatedField.freeAdjacentLocation(location);
			// Only transfer to the updated field if there was a free location
			if (newLocation != null) {
				setLocation(newLocation);
				updatedField.put(this, newLocation);
			} else {
				// can neither move nor stay - overcrowding - all locations taken
				alive = false;
			}
		}
	}

	/**
	 * Increase the age. This could result in the 's death.
	 */
	private void incrementAge() {
		age++;
		if (age > MAX_AGE) {
			alive = false;
		}
	}

	/**
	 * Generate a number representing the number of births, if it can breed.
	 * 
	 * @return The number of births (may be zero).
	 */
	private int breproduce() {
		int births = 0;
		if (canREPRODUCE() && rand.nextDouble() <= REPRODUCTION_PROBABILITY) {
			births = rand.nextInt(MAX_REPRODUCTION_SPEED) + 1;
		}
		return births;
	}

	/**
	 * A  can breed if it has reached the breeding age.
	 * 
	 * @return true if the  can breed, false otherwise.
	 */
	private boolean canREPRODUCE() {
		return age >= REPRODUCTION_AGE;
	}

	/**
	 * Check whether the  is alive or not.
	 * 
	 * @return true if the  is still alive.
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Tell the carrot that it's dead now :(
	 */
	public void setEaten() {
		alive = false;
	}

	/**
	 * Set the animal's location.
	 * 
	 * @param row
	 *            The vertical coordinate of the location.
	 * @param col
	 *            The horizontal coordinate of the location.
	 */
	public void setLocation(int row, int col) {
		this.location = new Location(row, col);
	}

	/**
	 * Set the 's location.
	 * 
	 * @param location
	 *            The carrot's location.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
}