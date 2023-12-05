/**
 * 戏曲评论
 */
export interface OperaComment {
	/**
	 * 评论编号
	 */
	commentId?: string

	/**
	 * 评论内容
	 */
	commentInfo?: string
	/**
	 * 用户编号
	 */
	playgoerId?: string
	/**
	 * 用户姓名
	 */
	playgoerName?: string
	/**
	 * 用户头像
	 */
	playgoerAvatar?: string
	/**
	 * 评论时间
	 */
	updatedAt?: Date
}
