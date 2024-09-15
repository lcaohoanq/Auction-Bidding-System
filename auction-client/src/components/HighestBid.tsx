import React from 'react';
import { Paper, Typography, Box } from '@mui/material';
import { Bid, BidListProps } from '../schemas/bid.schema';

const HighestBid: React.FC<BidListProps> = ({ bids }) => {
  const highestBid = bids.reduce((max, bid) => bid.amount > max.amount ? bid : max, { amount: 0, bidder: 'No bids yet' } as Bid);

  return (
    <Paper elevation={3} sx={{ p: 2, my: 2 }}>
      <Typography variant="h6" component="h2" gutterBottom>
        Highest Bid
      </Typography>
      <Box>
        <Typography variant="body1">
          Bidder: {highestBid.bidder}
        </Typography>
        <Typography variant="body1">
          Amount: ${highestBid.amount.toFixed(2)}
        </Typography>
        {highestBid.bidTime && (
          <Typography variant="body2" color="text.secondary">
            Time: {new Date(highestBid.bidTime).toLocaleString()}
          </Typography>
        )}
      </Box>
    </Paper>
  );
};

export default HighestBid;