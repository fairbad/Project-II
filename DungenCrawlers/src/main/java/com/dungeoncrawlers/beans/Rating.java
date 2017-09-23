package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="RATINGS")
public class Rating implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name="RATING_ID")
    @SequenceGenerator(name="RATING_ID_SEQ", sequenceName="RATING_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RATING_ID_SEQ")
    private int id;

    @Column()
    private int rating;
    
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
    @JoinColumn(name="CAMPAIGN_ID", nullable = false)
    private Campaign campaign;
    
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
    @JoinColumn(name="USER_ID", nullable = false)
    private User user;

    public Rating() {}
    
	public Rating(int id, int rating, Campaign campaign, User user) {
		super();
		this.id = id;
		this.rating = rating;
		this.campaign = campaign;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
