export interface Bid {
  auctionId: string;
  bidder: string;
  amount: number;
  bidTime: string;
}
  export interface BidListProps {
    bids: Bid[];
  }