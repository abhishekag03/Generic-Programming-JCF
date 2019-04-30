import java.util.*;
//import java.lang.*;
import java.io.*;


class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
	Node<T> left;
	Node<T> right;
	T val;
	
	public Node(T data) {
		val = data;
		left = null;
		right = null;
	}
	public Node<T> getLeft() {
		return left;
	}
	
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	
	public Node<T> getRight() {
		return right;
	}
	
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	public T getVal() {
		return val;
	}
	
	public void setVal(T val) {
		this.val = val;
	}
	
	@Override
	public int compareTo(Node<T> node2) {
		return (this.getVal().compareTo(node2.getVal()));
	}
	
	
}


class Tree<T extends Comparable<T>>{
	Node<T> root;
	ArrayList<Object> arrlist = new ArrayList<Object>();
//	public Tree() {
//		root = new Node<T>();
//	}
	public ArrayList<Object> getlist(){
		return this.arrlist;
	}
	public Node<T> insert(Node<T> node, T value){
//		System.out.println(value + " getting inserted");
		Node<T> tobeins = new Node<T>(value);
		if (node==null) {
//			node1 = node2;
			return new Node(value);
		}
		
		else {
			if (tobeins.compareTo(node)<=0) {
				node.left = insert(node.left, value);
			}
			
			else {
				node.right = insert(node.right, value);
			}
		}
		return node;
//		return node1;
	}
	
	public void inorder(Node<T> root) {
		if (root==null) {
			return;
		}
		inorder(root.getLeft());
		arrlist.add(root.getVal());
//		System.out.println("inorder added "+ root.getVal());
		inorder(root.getRight());
	}
	
	
	
	
}
public class Christmas {
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HashMap<Integer,ArrayList<Object>> hash = new HashMap<Integer,ArrayList<Object>>(); 
		Reader.init(System.in);
		BSTFilesBuilder bs = new BSTFilesBuilder();
		int numStudents = Reader.nextInt();
		int numTrees = Reader.nextInt();
		bs.createBSTFiles(numStudents, numTrees);
		for (int i=0;i<numStudents;i++) {
			hash.put(i+1, new ArrayList<Object>());
		}
		for (int i=1 ; i<=numTrees ; i++) {
			InputStream input = new FileInputStream("./src/" + i + ".txt"); 
//			Scanner scanned = new Scanner(new File("./src/" + i + ".txt"));
//			String type = br.readLine();
			Reader.init(input);
			String s = Reader.next();
			String str="";
			int intsum=0;
			float floatsum = 0;
			Tree object;
			if (s.equals("Integer")) {
//				Node<Integer> root1 = new Node<Integer>(Reader.nextInt());
				
				object = new Tree<Integer>();
				int numnodes = Reader.nextInt();
				int rootval = Reader.nextInt();
				intsum+=rootval;
				object.root = new Node<Integer>(rootval);
				
//				intobj.root.setVal(Reader.nextInt());
//				object.root=root1;
				for (int j=0;j<numnodes-1;j++) {
					int vall = Reader.nextInt();
					intsum+=vall;
//					System.out.println();
					object.insert(object.root ,vall);
				}
				object.inorder(object.root);
//				System.out.println("inorder int ");
//				for (int k=0;k<numStudents;k++) {
//					System.out.println("print check "+ object.arrlist.get(k));
//				}
				int rollno=1;
				
				while (true) {
					int num = (int) object.arrlist.get(rollno-1);
					if (num==rootval) {
						break;
					}
					rollno++;
//					System.out.println("while running!! DANGER");
				}
				ArrayList<Object> arr = hash.get(rollno);
				arr.add(intsum);
				hash.put(rollno, arr);
//				hash.put(rollno, arg1)
			}
			else if (s.equals("Float")) {
				object = new Tree<Float>();
				int numnodes = Reader.nextInt();
				Float rootval = Reader.nextFloat();
				floatsum+=rootval;
				object.root = new Node<Float>(rootval);
				
				for (int j=0;j<numnodes-1;j++) {
					float vall = Reader.nextFloat();
					floatsum+=vall;
					object.insert(object.root ,vall);
				}
				object.inorder(object.root);
//				for (int k=0;k<object.)
				int rollno = 1;
				while (true) {
					float num = (float) object.arrlist.get(rollno-1);
					if (num==rootval) {
						break;
					}
					rollno++;
				}
				ArrayList<Object> arr = hash.get(rollno);
				arr.add(floatsum);
				hash.put(rollno, arr);
				
			}
			else {
//				Node<Integer> root1 = new Node<Integer>(Reader.nextInt());
				object = new Tree<String>();
				int numnodes = Reader.nextInt();
				String rootname = Reader.next();
				object.root = new Node<String>(rootname);
				
//				intobj.root.setVal(Reader.nextInt());
//				object.root=root1;
				for (int j=0;j<numnodes-1;j++) {
					object.insert(object.root ,Reader.next());
//					System.out.println();
				}
				object.inorder(object.root);
				ArrayList<String> list = object.getlist();
//				System.out.println("size "+object.arrlist.size());
//				String s = (String)object.arrlist.get(i);
				for (int j=0;j<object.arrlist.size();j++) {
//					System.out.println("check "+object.arrlist.get(j));
//					System.out.println("goo "+list.get(j));
					str=str.concat(list.get(j));
//					System.out.println("str = "+ str);
				}
				int rollno = 1;
				while (true) {
					String st = (String) object.arrlist.get(rollno-1);
					if (st.equals(rootname)) {
						break;
					}
					rollno++;
				}
				ArrayList<Object> arr = hash.get(rollno);
				arr.add(str);
				hash.put(rollno, arr);
			}
//			System.out.println("int " + intsum);
//			System.out.println("float "+ floatsum);
//			System.out.println("str "+ str);
		}
//		System.out.println("num= "+ numStudents);
		PrintWriter w = new PrintWriter("./src/out.txt", "UTF-8");
//		System.out.println(floatsum);
//		System.out.println(floatsum);
//		System.out.println(floatsum);
		int n = numStudents;
		for (int k=0 ; k<numStudents ; k++) {
			ArrayList<Object> ar = hash.get(k+1);
			w.print(k+1 + " " );
//			System.out.print(k+1 + " " );
//			for (int i=0;i<ar.size();i++) {
////				System.out.println(i + " " + );
//				w.println(i + " " + );
//			}
			
			if (ar.size()!=0) {
				n--;
				for (int i=0;i<ar.size();i++) {
//					System.out.println(ar.get(i)+ " ");
//					w.print(ar.get(i)+ " " );
					w.print(ar.get(i)+ " " );
				}
			}
//			System.out.println();
			w.println();
		}
//		System.out.println(n);
		w.println(n);
		w.close();
	}	
}
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }
 
    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
 
    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

//@Ov1erride
//public int compareTo(<T> value) {
//	if (this.getVal() instanceof String && value instanceof String ) {
//		String s1 = (String)this.getVal();
//		String s2 = (String)data;
//		return s1.compareTo(s2);
//	}
//	
//	else if (this.getVal() instanceof Integer && node2.getVal() instanceof Integer ) {
//		int n1 = (int)this.getVal();
//		int n2 = (int)data;
//		if (n1<n2) {
//			return -1;
//		}
//		else if (n1>n2) {
//			return 1;
//		}
//		else {
//			return 0;
//		}
//	}
//	
//	else {
//		float n1 = (float)this.getVal();
//		float n2 = (float)data;
//		if (n1<n2) {
//			return -1;
//		}
//		else if (n1>n2) {
//			return 1;
//		}
//		else {
//			return 0;
//		}
//	}
//	this.getVal().compareTo(node2.getVal());
	
//}

//public Node<T> insert(Node<T> root, <T> val) {
//	if (root==null) {
//		root = new Node();
//	}
//	else {
//		if (val.compare);
//	}
//}