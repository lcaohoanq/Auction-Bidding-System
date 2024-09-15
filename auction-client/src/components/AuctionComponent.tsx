import React, { useEffect, useState } from 'react';
import { Container, Typography, Grid as MuiGrid } from '@mui/material';
import AuctionForm from './AuctionForm';
import BidList from './BidList';
import HighestBid from './HighestBid';
import { connectWebSocket, sendBid } from '../services/websocketService';
import { Bid } from '../schemas/bid.schema';

const AuctionComponent: React.FC = () => {
  const [bids, setBids] = useState<Bid[]>([]);
  const [bidAmount, setBidAmount] = useState<string>('');
  const [username, setUsername] = useState<string>('');

  useEffect(() => {
    const disconnect = connectWebSocket((bidMessage: Bid) => {
      setBids((prevBids) => [...prevBids, bidMessage]);
    });

    return disconnect;
  }, []);

  const handleBidChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setBidAmount(event.target.value);
  };

  const handleUsernameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handlePlaceBid = () => {
    const amount = parseFloat(bidAmount);
    if (isNaN(amount) || amount <= 0) {
      alert('Please enter a valid bid amount');
      return;
    }
    if (username.trim() === '') {
      alert('Please enter your username');
      return;
    }
  
    const bidMessage: Bid = {
      auctionId: "someAuctionId", // You need to provide a real auction ID
      bidder: username,
      amount: amount,
      bidTime: new Date().toISOString()
    };
    console.log('Sending bid:', JSON.stringify(bidMessage));
    sendBid(bidMessage);
    setBidAmount('');
    setUsername('');
  };

  return (
    <Container maxWidth="md">
      <Typography variant="h4" component="h1" gutterBottom>
        Auction
      </Typography>
      <MuiGrid container spacing={3}>
        <MuiGrid item xs={12} md={6}>
          <AuctionForm
            username={username}
            bidAmount={bidAmount}
            onUsernameChange={handleUsernameChange}
            onBidChange={handleBidChange}
            onPlaceBid={handlePlaceBid}
          />
          <HighestBid bids={bids} />
        </MuiGrid>
        <MuiGrid item xs={12} md={6}>
          <BidList bids={bids} />
        </MuiGrid>
      </MuiGrid>
    </Container>
  );
};

export default AuctionComponent;