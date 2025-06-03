package co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegistrarSesionRequestDTO {
    private UUID grupoId;
    private LocalDate fecha;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String aula;
    private String estado;
    
	public UUID getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(UUID grupoId) {
		this.grupoId = grupoId;
	}
	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}
	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}
	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
	public String getAula() {
		return aula;
	}
	public void setAula(String aula) {
		this.aula = aula;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


    
}
