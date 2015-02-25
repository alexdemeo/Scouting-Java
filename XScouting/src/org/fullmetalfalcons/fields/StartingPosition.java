package org.fullmetalfalcons.fields;

@Deprecated
public enum StartingPosition {
	S_PLATFORM("Starting Platform"),SZ_LEFT("Starting Zone Left"),SZ_CENTER("Starting Zone Center"),SZ_RIGHT("Starting Zone Right");
	
	private String name;
	
	private StartingPosition(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	public static StartingPosition getPosition(int raw){
		switch(raw){
			
		case 0:
			return S_PLATFORM;
		case 1:
			return SZ_LEFT;
		case 2:
			return SZ_CENTER;
		case 3:
			return SZ_RIGHT;
		}
		
		return null;
	}
}
