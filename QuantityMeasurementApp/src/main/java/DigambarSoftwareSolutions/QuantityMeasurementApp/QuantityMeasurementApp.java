package DigambarSoftwareSolutions.QuantityMeasurementApp;

public class QuantityMeasurementApp {
	
	// inner class Feet
	public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
        public double toInches() {
        	return value*12;
        }
        
        
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
        
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }
	
	// inner class INCHES
	
	public static class Inches{
		
		private final double value;
		
		public Inches(double value) {
			this.value = value;
		}
		
		public double getValue() {
			return value;
		}
		
		public double toFeet() {
			return value/12;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			if(obj == null) return false;
			
			// Inches vs Inches
			if(obj instanceof Inches) {
				Inches other = (Inches) obj;
				return Double.compare(this.value, other.value) == 0;
			}
			
			// Inches vs Feet (conversion)
			
			if(obj instanceof Feet) {
				Feet other = (Feet) obj;
				return Double.compare(this.value, other.toInches()) == 0;
			}
			return false;
		}
	}
	
	// SERVICE Methods
	
	public static boolean demonstrateFeetEquality(double a, double b) {
		return new Feet(a).equals(new Feet(b));
	}
	
	public static boolean demonstrateInchesEquality(double a, double b) {
		return new Inches(a).equals(new Inches(b));
	}

    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Equal: " + f1.equals(f2));
        
        System.out.println("Feet equality: " + demonstrateFeetEquality(1.0, 1.0));
        
        System.out.println("Inches equality: " + demonstrateInchesEquality(1.0, 1.0));
    }
}
