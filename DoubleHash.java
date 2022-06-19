// Name: Tom Rosen
// Email: trrosen @wisc.edu 
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
