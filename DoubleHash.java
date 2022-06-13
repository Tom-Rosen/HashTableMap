// --== CS400 Project One File Header ==--
// Name: Tom Rosen
// Email: trrosen @wisc.edu 
// Team: Blue
// Group: CI
// TA: TINGJIA CAO
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
//
public class DoubleHash<KeyType, ValueType>
{
        private KeyType key;
        private ValueType value;
        public DoubleHash(KeyType key, ValueType value)
        {
                this.key = key;
                this.value = value;
        }
        public KeyType getKey()
        {
                return this.key;
        }
        public ValueType getValue()
        {
                return this.value;
        }
        
}
