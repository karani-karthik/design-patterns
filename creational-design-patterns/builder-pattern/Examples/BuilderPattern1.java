
class House {

	private String foundation;
	private String structure;
	private String roof;
	private boolean hasGarage;
	private boolean hasGarden;
	private boolean hasSwimmingPool;

	private House(HouseBuilder builder) {
		this.foundation = builder.foundation;
		this.structure = builder.structure;
		this.roof = builder.roof;
		this.hasGarage = builder.hasGarage;
		this.hasGarden = builder.hasGarden;
		this.hasSwimmingPool = builder.hasSwimmingPool;
	}

	public static class HouseBuilder {
		private String foundation;
		private String structure;
		private String roof;
		private boolean hasGarage;
		private boolean hasGarden;
		private boolean hasSwimmingPool;

		public HouseBuilder(String foundation, String structure, String roof) {
			this.foundation = foundation;
			this.structure = structure;
			this.roof = roof;
		}

		public HouseBuilder withGarage() {
			this.hasGarage = true;
			return this;
		}

		public HouseBuilder withGarden() {
			this.hasGarden = true;
			return this;
		}

		public HouseBuilder withSwimmingPool() {
			this.hasSwimmingPool = true;
			return this;
		}

		public House build() throws IllegalAccessException {
			if (foundation == null || roof == null || structure == null) {
				throw new IllegalStateException("Required fields missing");
			}

			return new House(this);
		}
	}

	public String getFoundation() {
		return foundation;
	}

	public String getStructure() {
		return structure;
	}

	public String getRoof() {
		return roof;
	}

	public boolean isHasGarage() {
		return hasGarage;
	}

	public boolean isHasGarden() {
		return hasGarden;
	}

	public boolean isHasSwimmingPool() {
		return hasSwimmingPool;
	}

	@Override
	public String toString() {
		return "House [foundation=" + foundation + ", structure=" + structure + ", roof=" + roof + ", hasGarage="
				+ hasGarage + ", hasGarden=" + hasGarden + ", hasSwimmingPool=" + hasSwimmingPool + "]";
	}
}

public class BuilderPattern1 {
    public static void main(String[] args) throws IllegalAccessException {
        House h1 = new House.HouseBuilder("foundation", "structure", "roof")
                    .withGarage()
                    .withGarden()
                    .withSwimmingPool()
                    .build();

        System.out.println(h1);            
    }
}