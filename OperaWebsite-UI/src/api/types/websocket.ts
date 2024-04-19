import { Playgoer } from './user'

export interface SocketVO extends Playgoer {
	/**
	 * 消息编号
	 */
	msgId: string
	/**
	 * 内容
	 */
	msg: string
	/**
	 * 发送时间
	 */
	sendTime: Date
}
