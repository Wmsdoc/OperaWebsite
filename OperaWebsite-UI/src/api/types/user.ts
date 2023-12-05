export interface Playgoer {
	/**
	 * 用户编号
	 */
	playgoerId?: string
	/**
	 * 用户姓名
	 */
	playgoerName?: string
	/**
	 * 用户简介
	 */
	playgoerInfo?: string
	/**
	 * 用户性别
	 */
	playgoerSex?: string
	/**
	 * 用户生日
	 */
	playgoerBirthday?: Date
	/**
	 * 戏迷头像ID
	 */
	playgoerAvatar?: String
	/**
	 * 用户创建时间
	 */
	createdAt?: Date
	/**
	 * 用户更新时间
	 */
	updatedAt?: Date
	/**
	 * 创建人
	 */
	createdBy?: string
	/**
	 * 更新人
	 */
	updatedBy?: string
}
