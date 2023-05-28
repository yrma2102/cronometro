package com.coding.web.models;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Timer {
	private int id;
	private static int count = 0; 
	private Date inicio;
	private Date fin;
	public Timer() {
		this.id = ++count;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHourInicio() {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm a");
		if(this.inicio != null) {
			return  localDateFormat.format(this.inicio);
		}
		else {
			return "";
		}
	}
	public String getHourFin() {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm a");
		if(this.fin != null) {
			return  localDateFormat.format(this.fin);
		}
		else {
			return "";
		}
	}
	public String getHourTotal() {
		if(this.fin != null && this.inicio != null) {
			long seconds = (this.fin.getTime()-this.inicio.getTime())/1000;
			int minutos = (int)seconds/60;
			int segundos = (int)seconds% 60;
			return minutos + "m "+ segundos+"s";
		}
		return "";
	}

	
}
