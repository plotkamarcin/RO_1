package extractor;

import java.io.Serializable;

import featrueExtractors.HarrisEdgeExtractor;
import featrueExtractors.SobelEdgeExtractor;

public class StarImage extends Image implements Serializable,Extractable{

	private static final long serialVersionUID = -1314368100390599483L;
	
	StarImage(int[] image, int size, int label){
		super(image,size,label);
	}
    @Override
	public double calculateFirstFeature(){
    	//sobel edge detector
    	SobelEdgeExtractor sobel = new SobelEdgeExtractor();
    	int temp[] = new int[256*256];
    	temp = getImageTable();
    	sobel.init(temp, 256, 256);
    	int result[]=sobel.process();
    	result=sobel.threshold(result, 50);
    	double[] tmp = new double[256*256];
    	for(int i=0;i<result.length;i++){
    	tmp[i] = normalize(result[i], -16777216, -1);	
    	}
    	double area=0.0;
    	for(int i=0;i<tmp.length;i++){
    		if(tmp[i]>0){
    			area++;
    		}
    	}
    	
       return area;
	}
    @Override
	public double calculateSecondFeature(){
    	//harris corner detection
    	
    	double maxValue=0.0;
    	double minValue=0.0;
    	
    	HarrisEdgeExtractor harris = new HarrisEdgeExtractor();
    	int temp[] = new int[256*256];
    	int temp2[] = new int[256*256];
    	temp = getImageTable();
    	harris.init(temp, 256, 256, 0.10);
    	 int result[]=harris.process();
    	 HarrisEdgeExtractor harris2 = new HarrisEdgeExtractor();
         harris2.init(temp, 256, 256, 1.10);        
         temp2=harris2.process();
         int diff[]=new int[256*256];
         double diff2[] = new double[256*256];
         for(int i=0;i<diff.length;i++ ){
         	diff[i]=temp2[i]+5*result[i];
         }
         maxValue=minValue=diff[0];
                 
         for (int i=0;i<diff.length;i++){
         	if(diff[i]>maxValue){
         		maxValue=diff[i];
         	}
         	if(diff[i]<minValue){
         		minValue=diff[i];
         	}
         }
         for (int i=0;i<diff.length;i++){
         	if(diff[i]<1.57*maxValue){
         		diff[i]=(int)-16777216;
         	}
        	else{
        		diff[i]=-1;
        	}
         }
         for(int i=0;i<diff.length;i++){
     		diff2[i]= normalize(diff[i],-16777216,-1);
     	}
         double perimeter =0.0;
     	for(int i=1;i<10;i++){
     		for(int j=1;j<27;j++){
     			if(diff2[28*i+j]+1!=0 || 
     					diff2[28*i+j]-1!=0 || 
     							diff2[28*i+j]+28!=0||
     									diff2[28*i+j]-28!=0){
     				perimeter++;
     			}
     		}	
     	}
		return perimeter;
	}
    @Override
	public double calculateThirdFeature(){
    	//niemziennik momentowy rzêdu 1
    	double[]tmp= new double[256*256] ;
    	for(int i=0;i<super.getImageTable().length;i++){
    		tmp[i]= normalize(super.getImageTable()[i]);
    	}
    	double m00 = calculateMomentum(0, 0, tmp);
    	double m01 = calculateMomentum(0, 1, tmp);
    	double m10 = calculateMomentum(1, 0, tmp);
    	double m11 = calculateMomentum(1, 1, tmp);
    	double m20 = calculateMomentum(2, 0, tmp);
    	double m02 = calculateMomentum(0, 2, tmp);
    	double M02=m02-(Math.pow(m01, 2.0)/m00);
    	double M20=m20-(Math.pow(m10, 2.0)/m00);
    	return (M20+M02)/Math.pow(m00, 2.0);
	}
    public double calculateFourthFeature(){
    	//niemziennik momentowy rzêdu 3
    	double[]tmp= new double[256*256] ;
    	for(int i=0;i<super.getImageTable().length;i++){
    		tmp[i]= normalize(super.getImageTable()[i]);
    	}
    	double m00 = calculateMomentum(0, 0, tmp);
    	double m10 = calculateMomentum(1, 0, tmp);
    	double m01 = calculateMomentum(0, 1, tmp);
    	double m11 = calculateMomentum(1, 1, tmp);
    	double m02 = calculateMomentum(0, 2, tmp);
    	double m20 = calculateMomentum(2, 0, tmp);
    	double m21 = calculateMomentum(2, 1, tmp);
    	double m12 = calculateMomentum(1, 2, tmp);
    	double m30 = calculateMomentum(3, 0, tmp);
    	double m03 = calculateMomentum(0, 3, tmp);
    	
        double M30 = m30-3*m20*(m10/m00)+2*m10*Math.pow((m10/m00), 2.0);
        double M03 = m03-3*m02*(m01/m00)+2*m01*Math.pow((m01/m00), 2.0);
		double M12 = m12-2*m11*(m01/m00)-m02*(m10/m00)+2*m10*Math.pow((m01/m00), 2.0);
		double M21 = m21-2*m11*(m10/m00)-m20*(m01/m00)+2*m01*Math.pow((m10/m00), 2.0);
		
		return (Math.pow(M30+3*M12, 2.0)+Math.pow(3*M21-M03, 2.0))/Math.pow(m00, 5.0);
		
    }
	@Override
	public double getFeature5() {
		return 0.0;
	}
    

}
