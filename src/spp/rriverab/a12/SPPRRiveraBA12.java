//Nombre: Roberto Alain Rivera Bravo
//Matrícula: A01411516
//Carrera: IMT11

package spp.rriverab.a12;
import javax.swing.JOptionPane;

public class SPPRRiveraBA12 {

    public static void main(String[] args) {
        boolean flag;
        
        do{        
            menu(); //manda a método menú
            flag = salida(); //recibe el valor booleano
        } while (flag==false);
    }
    
    
    //método menú: muestra las opciones en pantalla
    public static void menu(){
        int option;
        boolean flag;   
        
        JOptionPane.showMessageDialog(null, "------------------------------------------------------\n"
        +   "Operaciones con matrices cuadradas\n"
        +   "------------------------------------------------------\n" , "Inicio", JOptionPane.PLAIN_MESSAGE);
        
        //Se crea método object para mostrar las opciones en el método showOptionDialog
        Object[] entradas = {
            "1. Suma de matrices",
            "2. Resta de matrices",
            "3. Multiplicacion de matrices",
            "4. Salir"
        };
        

        do {
            //Se elige una opción de operaciones de matrices
  
            option = JOptionPane.showOptionDialog(null, "Seleccione una opción ", "Menú de opciones ", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, entradas, null);
            
            //estructura if/else para elegir opciones
            if(option >= 0  && option <= 2){
                JOptionPane.showMessageDialog(null, "La opción seleccionada fue "+entradas[option], 
                "Confirmación", JOptionPane.WARNING_MESSAGE);
                crearMatrices(option+1);
                flag = true;
            } else if(option == 3){
                JOptionPane.showMessageDialog(null, "La opción seleccionada fue "+entradas[option], 
                "Confirmación", JOptionPane.WARNING_MESSAGE);
                flag = true;
            } else {
                JOptionPane.showMessageDialog(null, "Introduzca una opción válida ", 
                "ERROR", JOptionPane.ERROR_MESSAGE);
                flag=false;   
            }
   
        } while (flag==false); 
        
    }
    
    
    /* método crear matrices: relaciona a los métodos para crear matrices, asignarles datos
    y realizar operaciones con ellas, según la opción que recibe del método menú*/
    public static void crearMatrices(int option){
        int m;
        int[][] matrix; // crea la matriz sobre la que se mostrara la respuesta de la operación realizada
        
        //Se asigna la dimensión de la matriz
        JOptionPane.showMessageDialog(null, "Introduzca la dimensión de la matriz cuadrada", 
        "Dimensión", JOptionPane.INFORMATION_MESSAGE);
        m = verificarIntPositivo();
        
        //Se asignan valores a la matriz A
        JOptionPane.showMessageDialog(null, "Introduzca los valores de la matriz A ", 
        "Matriz A", JOptionPane.INFORMATION_MESSAGE);
        int[][]A = asignarValores(m);
        
        //Se asignan valores a la matriz B
        JOptionPane.showMessageDialog(null, "Introduzca los valores de la matriz B ", 
        "Matriz B", JOptionPane.INFORMATION_MESSAGE);
        int[][]B = asignarValores(m);
        

        switch (option) { //Menú switch donde con la opción elegida se realiza una operación con las matrices

            case 1:
                //manda a método suma de matrices
                matrix = sumaDeMatrices(A,B,m);
                //se muestran los arrays
                JOptionPane.showMessageDialog(null, mostrarArray(A), "La suma de la matriz A", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, mostrarArray(B), "Y la matriz B", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, mostrarArray(matrix), "Es:", JOptionPane.PLAIN_MESSAGE);
                break;
                
            case 2:
                //manda a método resta de matrices
                matrix = restaDeMatrices(A,B,m);
                //se muestran los arrays
                JOptionPane.showMessageDialog(null, mostrarArray(A), "La resta de la matriz A", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, mostrarArray(B), "Y la matriz B", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, mostrarArray(matrix), "Es:", JOptionPane.PLAIN_MESSAGE);
                break; 
                
            case 3:
                //manda a método multiplicacion de matrices
                matrix = multiplicacionDeMatrices(A,B,m);
                //se muestran los arrays
                JOptionPane.showMessageDialog(null, mostrarArray(A), "La multiplicación de la matriz A", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, mostrarArray(B), "Y la matriz B", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, mostrarArray(matrix), "Es:", JOptionPane.PLAIN_MESSAGE);
                break;
                
            default:
                break;
        }
     
    }
    
    //Método solicitar datos: permite verificar que los datos sean correctos y los captura
    public static int solicitarDatos(String message){
        int x = 0;
        boolean flag;

        do {
            try { //Intenta realizar las instrucciones
                x = Integer.parseInt(JOptionPane.showInputDialog(null, message));
                double a = (double)x;
                flag = true;
            } catch (Exception ex) { //Evita que el programa falle en caso de error y muestra el error
                JOptionPane.showMessageDialog(null, "Introduzca un número entero válido "+ex, 
                "ERROR", JOptionPane.ERROR_MESSAGE);
                flag = false;
            }
     
        } while (flag == false);
        
        return x; //regresa el valor verificado
    }
    
    /*Método verificar int positivo: verifica que el entero sea positivo para evitar introducir
    valores negativos en el tamaño de la matriz*/
    public static int verificarIntPositivo(){
        boolean flag;
        int x;
        
        //Ciclo do while: ejecuta al menos una vez las instrucciones en do, y evalúa si es cierto en while
        do {
            x = solicitarDatos("");
            //Evalúa si la x es menor a uno, y si lo es, asigna flag=false para repetir el ciclo
            if (x < 1) {
                JOptionPane.showMessageDialog(null, 
                "Introduzca un entero positivo(no hay longitudes negativas o nulas de matrices)", 
                "ERROR", JOptionPane.ERROR_MESSAGE);
                flag=false;
            } else{
                flag=true;  
            }
            
        } while (flag==false);
    
        return x; //regresa el valor comprobado
    }    
    
    //Método asignar valores: asigna los valores de una matriz con un ciclo for
    public static int[][] asignarValores(int m){
        //Crea el array con el tamaño introducido por el usuario anteriormente
        int[][] matrix = new int[m][m];
        
        //Ciclo for: Nos desplaza en las filas del array
        for (int i = 0; i < m; i++) {
            //Ciclo for: Nos desplzaza en las columnas del array
            for (int j = 0; j < m; j++) {
                //Asigna el valor del array en la posición i, j
                matrix[i][j] = solicitarDatos("Introduzca el valor de la posición "+i+","+j);
            }
        }
 
        return matrix; //regresa la matriz a la que se le asignaron valores
    }
    
    //Método suma de matrices: regresa una matriz que es suma de otras 2 que llegan al método
    public static int[][] sumaDeMatrices(int[][]A, int[][]B, int m){
        
        int[][] S = new int[m][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                S[i][j] = A[i][j] + B[i][j];
            }
        }

        return S; //regresa el array que es suma de las matrices
    }
    
    //Método suma de matrices: regresa una matriz que es resta de otras 2 que llegan al método
    public static int[][] restaDeMatrices(int[][]A, int[][]B, int m){
        
        int[][] R = new int[m][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                R[i][j] = A[i][j] - B[i][j];
            }
        }
        return R; //regresa el array que es resta de las matrices
    }
    
    //Método multiplicación de matrices: regresa una matriz que es producto matricial de AXB
    public static int[][] multiplicacionDeMatrices(int[][]A, int[][]B, int m){
        int[][]M = new int[m][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                /*Recorre los valores de columna en A y de fila en B para multiplicar
                cada elemento de la fila i de la matriz A por cada elemento de la columa j de la matriz B*/
                for (int k = 0; k < m; k++) {
                    M[i][j] += A[i][k]*B[k][j];
                }    
            }
        }
        
        return M; //regresa el array que es multiplicación de las matrices
    }
    
    //Método mostrar Array: muestra en pantalla la matriz creada
    public static String mostrarArray(int [][]matrix){ //Se recibe el objeto matrix
        String printMatrix = "";
        //Recorre las "i" filass del array
        for(int i=0; i<matrix.length; i++){
            //Recorre las "j" columnas del array
            for (int j=0; j<matrix[i].length; j++){
                printMatrix += "["+matrix[i][j]+"]";
                
            }                
            /*Introduce un salto de línea cada que se rebase la cantidad de columnas del array
            para que se vea el arreglo en orden en pantalla*/
            printMatrix += "\n";
        } 
        return printMatrix;
    }
    
    //Método salir: da opción de terminar o reiniciar los cálculos   
    public static boolean salida(){
        int option;
        boolean flag;
        
        option = JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?", 
        "Menú de salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        //verifica si el String es igual a "1"
        if(option == 1){
             //Se manda el valor booleano para volver a ejecutar el programa
            flag=false;
        } else{
            //Se manda el valor booleano para terminar el programa
           flag=true; 
        }
        
        return flag;  //Se regresa el valor booleano
    }
    
}
