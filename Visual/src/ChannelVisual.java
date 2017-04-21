

public class ChannelVisual extends Arrow {

	private double[] vektor = new double[2];
	double circleradius = 20;

	private Channel channel;
	
	public ChannelVisual (Channel channel, int[] coordinates){
		super();
		
		this.channel = channel;
		
		int id1 = 2*channel.getSender().getId();
		int id2 = 2*channel.getReceiver().getId();
		
		int x1 = coordinates[id1];
		int y1 = coordinates[id1+1];
		
		int x2 = coordinates[id2];
		int y2 = coordinates[id2+1];
		
		vektor[0]= x2-x1;
		vektor[1]= y2-y1;
		double length = Math.sqrt(Math.pow(vektor[0], 2)+Math.pow(vektor[1], 2));
		
		
		double factor = (length-circleradius)/length;
		
		
		vektor[0]= vektor[0]*factor;
		vektor[1]= vektor[1]*factor;
		x2= (int) (x1+vektor[0]);
		y2= (int) (y1+vektor[1]);
		

		
		this.setStartX(x1);
		this.setStartY(y1);
		this.setEndX(x2);
		this.setEndY(y2);

		
	}
	
}
