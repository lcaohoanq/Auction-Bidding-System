import React from 'react';
import { List, ListItem, ListItemText, Typography } from '@mui/material';
import { Bid, BidListProps } from '../schemas/bid.schema';


const BidList: React.FC<BidListProps> = ({ bids }) => {
  return (
    <List>
      {bids.map((bid: Bid, index: number) => (
        <ListItem key={index}>
          <ListItemText
            primary={`Bidder: ${bid.bidder}`}
            secondary={
              <React.Fragment>
                <Typography component="span" variant="body2" color="text.primary">
                  ${bid.amount.toFixed(2)}
                </Typography>
                {` - ${new Date(bid.bidTime).toLocaleString()}`}
              </React.Fragment>
            }
          />
        </ListItem>
      ))}
    </List>
  );
};

export default BidList;