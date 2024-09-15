import React from 'react';
import { TextField, Button, Box } from '@mui/material';

interface AuctionFormProps {
  username: string;
  bidAmount: string;
  onUsernameChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onBidChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onPlaceBid: () => void;
}

const AuctionForm: React.FC<AuctionFormProps> = ({ 
  username, 
  bidAmount, 
  onUsernameChange, 
  onBidChange, 
  onPlaceBid 
}) => {
  return (
    <Box component="form" sx={{ '& > :not(style)': { m: 1, width: '25ch' } }}>
      <TextField
        label="Username"
        variant="outlined"
        value={username}
        onChange={onUsernameChange}
      />
      <TextField
        label="Bid Amount"
        variant="outlined"
        type="number"
        value={bidAmount}
        onChange={onBidChange}
      />
      <Button variant="contained" onClick={onPlaceBid}>Place Bid</Button>
    </Box>
  );
};

export default AuctionForm;