export interface Bid {
  auctionId?: number;
  bidder: string;
  amount: number;
  bidTime: string;
}
  export interface BidListProps {
    bids: Bid[];
  }

  export interface Auction {
    id: number;
    itemName: string;
    highestBid: number;
    highestBidder: string;
  }