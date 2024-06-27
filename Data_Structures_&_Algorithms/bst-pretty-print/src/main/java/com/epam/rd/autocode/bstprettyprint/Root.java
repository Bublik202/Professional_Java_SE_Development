package com.epam.rd.autocode.bstprettyprint;

public class Root implements PrintableTree {
	private Node root;

	@Override
	public void add(int data) {
		if(root == null) {
			root = new Node(data);
		}else {
			root.insert(data);
		}
	}
	

	@Override
	public String prettyPrint() {
		String str = print(root, "", 1);	
		String[] array = str.split("\n");
		StringBuilder builder = new StringBuilder();
		
		for (String string : array) {
			if(string.charAt(string.length()-1) == ' ') {
				builder.append(string, 1, string.length()-1);				
			}else {
				builder.append(string.substring(1));
			}			
			builder.append("\n");
			
		}		
		return builder.toString();
	}
	public String print(Node root, String prefix, int dir) {
		if(root == null) {
			return "";
		}
		
		StringBuilder builderSpace = new StringBuilder();
		String space = builderSpace.append(" ".repeat(
				String.valueOf(root.getData()).length())).toString();
		
		String left = print(root.getLeftChild(), prefix + "│  ".charAt(dir) + space, 2);

		String mid = prefix + "└ ┌".charAt(dir) + root.getData() +
				" ┐┘┤".charAt(
				(root.getLeftChild() != null ? 2 : 0) + 
				(root.getRightChild() != null ? 1 : 0)) + "\n"; 
		
		String right = print(root.getRightChild(), prefix + "  │".charAt(dir) + space, 0);
		
		return left + mid + right;
	}

}

//   "┤"  "│"  "└"  "┘"  "┌"  "┐"