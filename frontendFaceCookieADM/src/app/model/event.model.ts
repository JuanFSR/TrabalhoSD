export namespace EventTypes {
    export enum Enum {
        ASSISTIDO_PROXIMO_ATENDIMENTO,
	    ASSISTIDO_DECREMENTAR_POSICAO,
        ASSISTIDO_QUANTIDADE_MEDICO_ONLINE,
    }

}   
export interface EventSocket<T> {
    topico: string,
    tipo: EventTypes.Enum,
    payload: Array<T> | T 
}