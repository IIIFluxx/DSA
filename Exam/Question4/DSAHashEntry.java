// Question 4 code

public class DSAHashEntry
{

    //class fields
    private String key;
    private Object value; 
    private int state; //0 = never used/free,  1 = used / not free

    //default constructor
    public DSAHashEntry() {

        key = "";
        value = null;
        state = 0;
    }

    //alternate constructor
    public DSAHashEntry(String inKey, Object inValue) 
	{
        key = inKey;
        value = inValue;
        state = 1;
    }

    public String getKey() 
	{

        return this.key;
    }

    public Object getValue() 
	{

        return this.value;
    }


    public int getState() 
	{

        return this.state;
    }

    public void setKey(String inKey) {

        this.key = inKey;
    }

    public void setValue(Object inValue) {

        this.value = inValue;
    }

    public void setState() {

        this.state = -1;
    }
}