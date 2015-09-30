package com.gmail.simpson.o.alexis;

final public class Calculation {
	 
	//calculates capcitance to a unity
	 static double calculateCapcitance(double power, double voltage, double frequency, double pf){
		 
		 double angle,rPower,rCurrent,Xc,capacitance;
		 
		//step 1 calculate phase angle
			angle =  Math.acos(pf)*180/Math.PI;
			
			//step 2 calculate reactive power
			// tan angle = power/rpower
			// using  power in watts not kilowats so must *1000
			rPower = (Math.tan(Math.toRadians(angle)))*(power*1000);
			
			//step 3 calculate reactive current
			//Ic= rpower/voltage
			rCurrent = rPower/voltage;
			
			// step 4 calculate inductive capcitance
			Xc= voltage/rCurrent;
			
			//step5 calculate capcitance
			// cap = 1/2*3.14*f*Xc
	    	capacitance=1*1000000/(2.0*3.14*frequency*Xc);
	    	return capacitance;
		 
	 }
	 //calculates the capacitance to a point below unity (1)
	 static double calculateCapcitance(double power, double voltage, double frequency,

			 double existing_PF,double required_PF){
		 
		 double existing_angle,required_angle,existing_rPower,required_rPower,rCurrent,Xc,capacitance;
		 //calculate angles
		 existing_angle = Math.acos(existing_PF)*180/Math.PI;
		 required_angle = Math.acos(required_PF)*180/Math.PI;
		 
		 //calculate base of large triangle using existing angle tan@ opp/adj
		// tan angle = power/rpower, power in watts
		 existing_rPower = (Math.tan(Math.toRadians(existing_angle)))*(power*1000);
		 
		 //calculate base of small triangle using required angle
		 required_rPower = (Math.tan(Math.toRadians(required_angle)))*(power*1000);
		 
		 //subtract base of small triangle from base of large triangle
		 double true_rPower = existing_rPower - required_rPower;
		 
		    // calculate reactive current
			//Ic= rpower/voltage
			rCurrent = true_rPower/voltage;
			
			// calculate inductive capacitance
			Xc= voltage/rCurrent;
			
			//calculate capacitance
			// cap = 1/2*3.14*f*Xc
	    	capacitance=1*1000000/(2.0*3.14*frequency*Xc);
	    	return capacitance;
		 
		 
		 
		 
	 }

	 //pf = kw/kva
	 //calculate true power
	 static double calculateTruePower(double apparentPower, double powerFactor){		 
		 return (apparentPower*powerFactor);
	 }
	 static double calculateApparentPower(double truePower, double powerFactor){
		 return (truePower/powerFactor);
		 }
	 static double calculatePowerFactor(double truePower, double apparentPower){
		return (truePower/apparentPower); 
	 }
 }
