 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class handler implements ActionListener{
    Gui ref;
    public handler(Gui ref){
        this.ref = ref;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("1") ||ae.getActionCommand().equals("2") ||ae.getActionCommand().equals("3") ||
            ae.getActionCommand().equals("4") ||ae.getActionCommand().equals("5")||ae.getActionCommand().equals("6") ||
            ae.getActionCommand().equals("7") ||ae.getActionCommand().equals("8")||ae.getActionCommand().equals("9") ||
            ae.getActionCommand().equals("0") ||ae.getActionCommand().equals(".")){
            if(ref.in.getText().equals("INFINITY") )// resetting in case of infinity
                ref.in.setText("");
            if(!ref.out.getText().equals("")){//if there is something on output field already.
                if(!isNumber(ref.out.getText().charAt(ref.out.getText().length()-1))){//if last bit is not operator
                    String text;
                    text = ref.in.getText();
                    if(text.length() <= 15){ // text doesn't goes out of screen.
                        if(ae.getActionCommand().equals(".")){//to check if point is already there.
                            if(text.equals(""))// to do 0.
                                text += "0"+ae.getActionCommand();
                            else if(validatePoint(text))
                                text += ae.getActionCommand();
                        }
                        else
                           text += ae.getActionCommand();    
                        ref.in.setText(text);
                    }
                }
            }
            else{
                String text;
                text = ref.in.getText();
                if(text.length() <= 15){ // text doesn't goes out of screen.
                    if(ae.getActionCommand().equals(".")){//to check if point is already there.
                            if(text.equals(""))// to do 0.
                                text += "0"+ae.getActionCommand();
                            else if(validatePoint(text))
                                text += ae.getActionCommand();
                        }
                        else
                           text += ae.getActionCommand();
                    ref.in.setText(text);
                }
            }
        }
        else if(ae.getActionCommand().equals("+") ||ae.getActionCommand().equals("-") ||ae.getActionCommand().equals("*")||
                ae.getActionCommand().equals("^") ||ae.getActionCommand().equals("/") ||ae.getActionCommand().equals("%")){
            if(ref.in.getText().equals("INFINITY")) // resetting in case of infinity
                ref.in.setText("");
            if(!ref.in.getText().equals("")){// to prevent from just an operator
                String fromin = ref.in.getText() + ae.getActionCommand();
                ref.in.setText("");
                String fromout = ref.out.getText();
                if(fromout.equals("")){
                    fromout += fromin;
                    ref.out.setText(fromout);
                }
                else{
                    ref.out.setText(calculate(fromout, fromin));
                    ref.in.setText("");
                }
            }
            else if(ref.in.getText().equals("") && !ref.out.getText().equals("")){ //to change sign at output
                if(!isNumber(ref.out.getText().charAt(ref.out.getText().length()-1)))//make sure if last bit is operator
                    ref.out.setText(ref.out.getText().substring(0, ref.out.getText().length()-1) + ae.getActionCommand());
                else{//if last bit is not an operator  {equal has been pressed}
                    ref.out.setText(ref.out.getText() + ae.getActionCommand());
                }
            }
        }
        else if(ae.getActionCommand().equals("+/-")){
            if(ref.in.getText().equals("INFINITY")) // resetting in case of infinity
                ref.in.setText("");
            double number = Double.parseDouble(ref.in.getText());
            if(!ref.in.getText().equals("") && number != 0){ // input field is not empty and not equal 0
                if(ref.in.getText().charAt(0) != '-'){
                    String text = "-" + ref.in.getText();
                    ref.in.setText(text);
                }
                else{
                    String text = ref.in.getText().substring(1, ref.in.getText().length());
                    ref.in.setText(text);
                }
            }
        }
        else if(ae.getActionCommand().equals("<<")){
            if(ref.in.getText().equals("INFINITY")) // resetting in case of infinity
                ref.in.setText("");
            if(!ref.in.getText().equals("")){
                String text= ref.in.getText().substring(0, ref.in.getText().length()-1);
                if(text.length() == 1 && !isNumber(text.charAt(0))){ // to prevent from a single negative sign
                    ref.in.setText("");
                }
                else  
                    ref.in.setText(text);
            }
        }
        else if(ae.getActionCommand().equals("=")){
            String fIN = ref.in.getText();
            String fOUT = ref.out.getText();
            if(!fIN.equals("") && !fOUT.equals("")){
                ref.in.setText(calculate(fOUT, fIN));
                ref.out.setText("");
            }
        }
        else if(ae.getActionCommand().equals("C")){
            ref.in.setText("");
            ref.out.setText("");
        }
        else if(ae.getActionCommand().equals("CE")){
            ref.in.setText("");
        }
    }
    private boolean isOperator(char i){
        if(i == '+' || i == '-' ||i == '*' ||i == '/' ||i == '^' ||i == '%')
            return true;
        else return false;
    }
    private double power(double base, double expo){
        int pow = 1;
        for(int i=1;i<=expo;i++){
            pow *= base;
        }
        return pow;
    }
    private String calculate(String number1, String number2){
        char currentOperator = number1.charAt(number1.length()-1);
        number1 = number1.substring(0, number1.length()-1);
        double operand1 = Double.parseDouble(number1);
        char newOperator = ' ';
        if(!isNumber(number2.charAt(number2.length()-1))){  // for = button, it doesnt expect any operator at end
            newOperator = number2.charAt(number2.length()-1);
            number2 = number2.substring(0, number2.length()-1);
        }
        double operand2 = Double.parseDouble(number2);
        String result = "";
        switch(currentOperator){
        case '+':
            result = String.valueOf(operand1 + operand2);
            break;
        case '-':
            result = String.valueOf(operand1 - operand2);
            break;
        case '*':
            result = String.valueOf(operand1 * operand2);
            break;
        case '/':
            if(operand2 != 0)
                result = String.valueOf(operand1 / operand2);
            else
                result = "INFINITY";
            break;
        case '^':
            result = String.valueOf(power(operand1 , operand2));
            break;
        case '%':
            result = String.valueOf(operand1 % operand2);
            break;
        }
        if(!result.equals("INFINITY"))
            result = removePoint(result);
        if(newOperator != ' ')
            return result + newOperator;
        else
            return result;
    }
    private String removePoint(String result){
        String[] temp = result.split("\\.");
        for(int i=0; i<temp[1].length();i++){
            if(temp[1].charAt(i) != '0'){
                return result;
            }
        }
        return temp[0];
    }
    private boolean isNumber(char i){
        return (i >= '0' && i <= '9');
    }
    private boolean validatePoint(String text){
        if(!text.equals("")){
            for(int i=0; i<text.length();i++){
                if(text.charAt(i) == '.')return false;
            }
            return true;
        }
        else return false;
    }
}
