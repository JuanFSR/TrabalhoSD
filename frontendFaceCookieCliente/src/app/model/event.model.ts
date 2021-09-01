export namespace EventTypes {
    export enum Enum {
        SALA_CRIADA,
        JOGADOR_ENTROU_SALA,
        JOGADOR_SAIU_SALA,
        JOGO_INICIOU,
        JOGADOR_JOGOU,
        RESULTADO
    }

}   
export interface EventSocket<T> {
    topico: string,
    tipo: EventTypes.Enum,
    payload: Array<T> | T 
}