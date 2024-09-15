import SockJS from 'sockjs-client';
import { Client, Message, Stomp } from '@stomp/stompjs';
import { Bid } from '../schemas/bid.schema';

let stompClient: Client | null = null;

export const connectWebSocket = (onMessageReceived: (bid: Bid) => void): (() => void) => {
  const socket = new SockJS('http://localhost:8080/auction-websocket');
  stompClient = new Client({
    webSocketFactory: () => socket,
  });

  stompClient.onConnect = () => {
    console.log('Connected to WebSocket');
    stompClient?.subscribe('/topic/auction', (message: Message) => {
      console.log('Received message:', message.body);
      const bidMessage: Bid = JSON.parse(message.body);
      onMessageReceived(bidMessage);
    });
  };

  stompClient.onStompError = (frame) => {
    console.error('STOMP error:', frame);
  };

  stompClient.activate();

  return () => {
    if (stompClient) {
      stompClient.deactivate();
    }
  };
};

export const sendBid = (bidMessage: Bid): void => {
  if (stompClient && stompClient.connected) {
    stompClient.publish({
      destination: '/app/placeBid',
      body: JSON.stringify(bidMessage),
    });
  } else {
    console.error('WebSocket connection is not active');
  }
};