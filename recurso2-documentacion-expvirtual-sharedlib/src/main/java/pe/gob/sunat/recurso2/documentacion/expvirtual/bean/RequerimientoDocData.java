package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RequerimientoDocData {
	@JsonProperty("codTipDoc")
	private String codTipDoc;
	@JsonProperty("desMotSolDoc")
	private String desMotSolDoc;
	
	//INICIO CVG
			@JsonProperty("nom_arc_adj")
			private String nom_arc_adj;
			@JsonProperty("num_archivo")
			private String num_archivo;
			@JsonProperty("cnt_tam_arc")
			private Integer cnt_tam_arc;
			@JsonProperty("num_folios")
			private Integer num_folios;
			@JsonProperty("cod_idecm")
			private String cod_idecm;
			@JsonProperty("cod_origdoc")
			private String cod_origdoc;
			@JsonProperty("cod_usuregis")
			private String cod_usuregis;
			//FIN CVG

	public String getCodTipDoc() {
		return codTipDoc;
	}

	public void setCodTipDoc(String codTipDoc) {
		this.codTipDoc = codTipDoc;
	}

	public String getDesMotSolDoc() {
		return desMotSolDoc;
	}

	public void setDesMotSolDoc(String desMotSolDoc) {
		this.desMotSolDoc = desMotSolDoc;
	}
	
	//INicio CVG
		public String getNom_arc_adj() {
			return nom_arc_adj;
		}

		public void setNom_arc_adj(String nom_arc_adj) {
			this.nom_arc_adj = nom_arc_adj;
		}

		public String getNum_archivo() {
			return num_archivo;
		}

		public void setNum_archivo(String num_archivo) {
			this.num_archivo = num_archivo;
		}

		public Integer getCnt_tam_arc() {
			return cnt_tam_arc;
		}

		public void setCnt_tam_arc(Integer cnt_tam_arc) {
			this.cnt_tam_arc = cnt_tam_arc;
		}

		public Integer getNum_folios() {
			return num_folios;
		}

		public void setNum_folios(Integer num_folios) {
			this.num_folios = num_folios;
		}

		public String getCod_idecm() {
			return cod_idecm;
		}

		public void setCod_idecm(String cod_idecm) {
			this.cod_idecm = cod_idecm;
		}

		public String getCod_origdoc() {
			return cod_origdoc;
		}

		public void setCod_origdoc(String cod_origdoc) {
			this.cod_origdoc = cod_origdoc;
		}

		public String getCod_usuregis() {
			return cod_usuregis;
		}

		public void setCod_usuregis(String cod_usuregis) {
			this.cod_usuregis = cod_usuregis;
		}
		
		//Fin CVG
	
	
	
	
}